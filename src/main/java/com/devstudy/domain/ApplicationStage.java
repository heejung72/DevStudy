package com.devstudy.domain;

public enum ApplicationStage {
    DOCUMENT("서류"),
    CODING_TEST("코딩 테스트"),
    FIRST_INTERVIEW("1차 면접"),
    SECOND_INTERVIEW("2차 면접"),
    FINAL_INTERVIEW("최종 면접");

    private final String label;

    ApplicationStage(String label) { this.label = label; }
    public String getLabel() { return label; }
}
