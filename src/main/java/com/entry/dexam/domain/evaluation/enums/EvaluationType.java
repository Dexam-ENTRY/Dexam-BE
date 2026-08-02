package com.entry.dexam.domain.evaluation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvaluationType {
    EXAM("EXAM"),
    PERFORMANCE("PERFORMANCE");

    private final String key;
}
