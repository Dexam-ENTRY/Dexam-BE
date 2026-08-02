package com.entry.dexam.domain.auth.entity;

import com.entry.dexam.domain.auth.enums.Role;
import com.entry.dexam.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String provider;
    private String providerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "grade", referencedColumnName = "grade", nullable = true),
            @JoinColumn(name = "class_num", referencedColumnName = "class_num", nullable = true)
    })
    private ClassInfo classInfo;


    @Builder
    public User(String email, String name, Role role, String provider, String providerId) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public User update(String name) {
        this.name = name;
        return this;
    }

    public User setClass(ClassInfo classInfo) {
        this.classInfo = classInfo;
        return this;
    }
}