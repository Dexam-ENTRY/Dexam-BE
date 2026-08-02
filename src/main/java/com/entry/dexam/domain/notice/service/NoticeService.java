package com.entry.dexam.domain.notice.service;

import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.enums.Role;
import com.entry.dexam.domain.auth.repository.ClassInfoRepository;
import com.entry.dexam.domain.auth.repository.UserRepository;
import com.entry.dexam.domain.notice.dto.request.NoticeCreateRequest;
import com.entry.dexam.domain.notice.dto.request.NoticeUpdateRequest;
import com.entry.dexam.domain.notice.dto.response.*;
import com.entry.dexam.domain.notice.entity.Notice;
import com.entry.dexam.domain.notice.enums.Target;
import com.entry.dexam.domain.notice.repository.NoticeRepository;
import com.entry.dexam.global.exception.exceptions.*;
import com.entry.dexam.global.exception.exceptions.ClassNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final ClassInfoRepository classInfoRepository;

    public NoticeCreateResponse createNotice(Long userId, NoticeCreateRequest request){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(user.getRole() != Role.ADMIN) throw NoticeWriteForbiddenException.EXCEPTION;

        ClassInfo classInfo = resolveClassInfo(request.target(), request.grade(), request.classNo());

        Notice notice = Notice.builder()
                .user(user)
                .title(request.title())
                .content(request.content())
                .target(request.target())
                .classInfo(classInfo)
                .build();

        noticeRepository.save(notice);

        return NoticeCreateResponse.from(notice);
    }

    @Transactional(readOnly = true)
    public NoticeListResponse readNoticeList(Long userId, Target target, String keyword){

        String normalizedKeyword = keyword == null || keyword.isBlank() ? null : keyword.trim();
        List<Notice> notices = new ArrayList<>();

        if(target == Target.ALL) {
            notices = noticeRepository.findNotices(target, normalizedKeyword);

        } else if (target == Target.CLASS) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> UserNotFoundException.EXCEPTION);

            if (user.getClassInfo() == null) throw UnauthorizedException.EXCEPTION;

            notices = noticeRepository.findClassNotices(target,
                    user.getClassInfo().getClassId().getGrade(),
                    user.getClassInfo().getClassId().getClassNum(),
                    normalizedKeyword
            );
        }

        return NoticeListResponse.from(notices);
    }

    @Transactional(readOnly = true)
    public NoticeDetailResponse readNoticeDetail(Long userId, Long noticeId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        validateReadPermission(user, notice);

        return NoticeDetailResponse.from(notice);
    }

    public NoticeUpdateResponse updateNotice(Long userId, Long noticeId, NoticeUpdateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(user.getRole() != Role.ADMIN) throw NoticeWriteForbiddenException.EXCEPTION;

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        ClassInfo classInfo = resolveClassInfo(request.target(), request.grade(), request.classNo());

        notice.update(request.title(), request.content(), request.target(), classInfo);

        return NoticeUpdateResponse.from(notice);
    }

    public NoticeDeleteResponse deleteNotice(Long userId, Long noticeId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(user.getRole() != Role.ADMIN) throw NoticeWriteForbiddenException.EXCEPTION;

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        noticeRepository.delete(notice);

        return NoticeDeleteResponse.of();
    }

    private void validateReadPermission(User user, Notice notice){
        if(notice.getTarget() == Target.ALL) return;

        if(user.getClassInfo() == null || !user.getClassInfo().getClassId()
                .equals(notice.getClassInfo().getClassId())
        ) throw UnauthorizedException.EXCEPTION;
    }

    private ClassInfo resolveClassInfo(Target target, Integer grade, Integer classNo) {
        if (target == Target.ALL) return null;

        if (grade == null || classNo == null) throw ValidationFailedException.EXCEPTION;

        return classInfoRepository.findByClassIdGradeAndClassIdClassNum(
                grade, classNo
        ).orElseThrow(() -> ClassNotFoundException.EXCEPTION);
        }
    }

