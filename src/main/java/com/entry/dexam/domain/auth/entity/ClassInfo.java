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
    private ClassId classId;

    public ClassInfo(int grade, int classNum) {
        this.classId = new ClassId(grade, classNum);
    }
}