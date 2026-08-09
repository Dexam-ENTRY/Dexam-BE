package com.entry.dexam.domain.schedule;

import com.entry.dexam.domain.schedule.dto.ScheduleCreateRequest;
import com.entry.dexam.domain.schedule.dto.ScheduleItemResponse;
import com.entry.dexam.domain.schedule.dto.ScheduleListResponse;
import com.entry.dexam.domain.schedule.dto.ScheduleUpdateRequest;
import com.entry.dexam.global.exception.exceptions.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entry.dexam.domain.schedule.repository.ScheduleRepository;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long createSchedule(ScheduleCreateRequest request) {
        Schedule schedule = Schedule.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .date(request.getDate())
                .target(request.getTarget())
                .targetGrade(request.getTargetGrade())
                .targetClassNo(request.getTargetClassNo())
                .build();

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return savedSchedule.getId();
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
    public ScheduleListResponse getSchedules(LocalDate startDate, LocalDate endDate, Integer grade, Integer classNo) {

        List<Schedule> schedules = scheduleRepository.searchSchedules(startDate, endDate, grade, classNo);

        List<ScheduleItemResponse> items = schedules.stream()
                .map(ScheduleItemResponse::from)
                .toList();

        return new ScheduleListResponse(items);
    }
}