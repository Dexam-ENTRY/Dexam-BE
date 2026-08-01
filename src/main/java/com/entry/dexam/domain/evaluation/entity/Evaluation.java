package com.entry.dexam.domain.evaluation.entity;

import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.evaluation.dto.EvaluationPatchRequest;
import com.entry.dexam.domain.evaluation.enums.EvaluationType;
import com.entry.dexam.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "evaluations")
@Builder
public class Evaluation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "grade", referencedColumnName = "grade", nullable = false),
            @JoinColumn(name = "class_num", referencedColumnName = "class_num", nullable = false)
    })
    private ClassInfo classInfo;

    @Column(length = 100)
    private String title;

    @Column(length = 1000)
    private String content;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private EvaluationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private User lastModifiedUser;

    public void update(EvaluationPatchRequest request, User user) {
        if (request.type() != null) {
            this.type = request.type();
        }

        if (request.title() != null && !request.title().isBlank()) {
            this.title = request.title();
        }

        if (request.content() != null && !request.content().isBlank()) {
            this.content = request.content();
        }

        if (request.date() != null) {
            this.date = request.date();
        }

        this.lastModifiedUser = user;
    }
}
