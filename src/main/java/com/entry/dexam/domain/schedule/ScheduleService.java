package com.entry.dexam.domain.schedule;

import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.repository.UserRepository;
import com.entry.dexam.domain.schedule.dto.ScheduleCreateRequest;
import com.entry.dexam.domain.schedule.dto.ScheduleItemResponse;
import com.entry.dexam.domain.schedule.dto.ScheduleListResponse;
import com.entry.dexam.domain.schedule.dto.ScheduleUpdateRequest;
import com.entry.dexam.global.exception.exceptions.ScheduleNotFoundException;
import com.entry.dexam.global.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entry.dexam.domain.schedule.repository.ScheduleRepository;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createSchedule(ScheduleCreateRequest request) {
        return scheduleRepository.save(request.toEntity()).getId();
    }
    @Transactional
    public void updateSchedule(Long scheduleId, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> ScheduleNotFoundException.EXCEPTION);

        schedule.update(
                request.title(),
                request.content(),
                request.date(),
                request.target(),
                request.targetGrade(),
                request.targetClassNo()
        );
    }
    @Transactional
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> ScheduleNotFoundException.EXCEPTION);
        scheduleRepository.delete(schedule);
    }
    @Transactional(readOnly = true)
    public ScheduleListResponse getAdminSchedules(LocalDate startDate, LocalDate endDate, Integer grade, Integer classNo, Long userId) {
        List<Schedule> schedules = scheduleRepository.searchAdminSchedules(startDate, endDate, grade, classNo);

        List<ScheduleItemResponse> items = schedules.stream()
                .map(ScheduleItemResponse::from)
                .toList();

        return new ScheduleListResponse(items);
    }
    @Transactional(readOnly = true)
    public ScheduleListResponse getSchedules(LocalDate startDate, LocalDate endDate, Long userId) {

        User user = userRepository.findByIdWithClassInfo(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        ClassInfo classInfo = user.getClassInfo();

        List<Schedule> schedules = scheduleRepository.searchSchedules(startDate, endDate, classInfo.getClassId().getGrade(), classInfo.getClassId().getClassNum());

        List<ScheduleItemResponse> items = schedules.stream()
                .map(ScheduleItemResponse::from)
                .toList();

        return new ScheduleListResponse(items);
    }
}