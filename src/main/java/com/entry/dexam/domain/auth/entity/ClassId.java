package com.entry.dexam.domain.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClassId implements Serializable {

    @Column(name = "grade")
    private int grade;

    @Column(name = "class_num")
    private int classNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassId classId)) return false;
        return grade == classId.grade &&
                classNum == classId.classNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, classNum);
    }
}
