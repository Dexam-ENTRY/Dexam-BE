package com.entry.dexam.domain.evaluation.entity;

import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.evaluation.enums.EvaluationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "evaluations")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "grade", referencedColumnName = "grade", nullable = true),
            @JoinColumn(name = "class_num", referencedColumnName = "class_num", nullable = true)
    })
    private ClassInfo classInfo;

    private String title;

    private String content;

    private LocalDate date;

    private EvaluationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private User lastModifiedUser;

    private LocalDateTime created_at;
}
