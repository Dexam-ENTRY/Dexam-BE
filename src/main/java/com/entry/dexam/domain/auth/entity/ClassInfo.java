package com.entry.dexam.domain.auth.entity;

import com.entry.dexam.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "class")
public class ClassInfo extends BaseEntity {
    @EmbeddedId
    private ClassId classId;

    public ClassInfo(int grade, int classNum) {
        this.classId = new ClassId(grade, classNum);
    }
}