package com.entry.dexam.domain.schedule;

import com.entry.dexam.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "schedule_title", nullable = false)
    private String title;

    @Column(name = "schedule_content", nullable = false)
    private String content;

    @Column(name = "schedule_date", nullable = false)
    private LocalDate date;

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "class_no")
    private Integer classNo;

    @Builder
    public Schedule(String title, String content, LocalDate date, String target, Integer grade, Integer classNo) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.target = target;
        this.grade = grade;
        this.classNo = classNo;
    }
}