package com.entry.dexam.domain.auth.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "class")
public class ClassInfo {
    @EmbeddedId
    private ClassPk classPk;

    public ClassInfo setClass(int grade, int classNum) {
        this.classPk = new ClassPk(grade, classNum);
        return this;
    }
}