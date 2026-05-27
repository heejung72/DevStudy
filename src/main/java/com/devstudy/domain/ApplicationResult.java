package com.devstudy.domain;

public enum ApplicationResult {
    IN_PROGRESS("진행중"),
    PASSED("합격"),
    FAILED("불합격"),
    WITHDRAWN("취소");

    private final String label;

    ApplicationResult(String label) { this.label = label; }
    public String getLabel() { return label; }
}
