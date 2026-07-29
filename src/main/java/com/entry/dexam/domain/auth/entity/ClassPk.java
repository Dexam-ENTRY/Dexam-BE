package com.entry.dexam.domain.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ClassPk implements Serializable {

    @Column(name = "grade")
    private int grade;

    @Column(name = "class_num")
    private int classNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassPk classPk)) return false;
        return grade == classPk.grade &&
                classNum == classPk.classNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, classNum);
    }
}
