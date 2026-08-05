package com.entry.dexam.domain.notice.entity;

import com.entry.dexam.domain.notice.enums.Target;
import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "announcements")
public class Notice extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassInfo classInfo;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Target target;


    @Builder
    public Notice(User user, ClassInfo classInfo, Target target, String title, String content){
        this.user = user;
        this.classInfo = classInfo;
        this.target = target;
        this.title = title;
        this.content = content;
    }


    public void update(String title, String content, Target target, ClassInfo classInfo) {
        this.title = title;
        this.content = content;
        this.target = target;
        this.classInfo = classInfo;
    }
}
