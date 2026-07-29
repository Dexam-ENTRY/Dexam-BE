package com.entry.dexam.domain.auth.service;

import com.entry.dexam.domain.auth.dto.ClassInfoDto;
import com.entry.dexam.domain.auth.dto.MeResponse;
import com.entry.dexam.domain.auth.dto.SetClassRequest;
import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.ClassPk;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.repository.ClassInfoRepository;
import com.entry.dexam.domain.auth.repository.UserRepository;
import com.entry.dexam.global.exception.exceptions.ClassNotFoundException;
import com.entry.dexam.global.exception.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ClassInfoRepository classInfoRepository;

    @Transactional
    public void updateClass(SetClassRequest dto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        ClassPk classPk = new ClassPk(
                dto.grade(), dto.classNum()
        );

        ClassInfo classInfo = classInfoRepository.findById(classPk)
                .orElseThrow(() -> ClassNotFoundException.EXCEPTION);

        user.setClass(classInfo);
    }

    public MeResponse getMe(String email) {
        User user = userRepository.findByEmailWithClassInfo(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        ClassInfoDto classInfoDto = null;

        ClassInfo classInfo = user.getClassInfo();
        if (classInfo != null) {
            ClassPk classPk = classInfo.getClassPk();
            classInfoDto = new ClassInfoDto(
                    classPk.getGrade(),
                    classPk.getClassNum()
            );
        }

        return new MeResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole(),
                classInfoDto
        );
    }
}
