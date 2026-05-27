package com.devstudy.dto;

import com.devstudy.domain.ApplicationResult;
import com.devstudy.domain.ApplicationStage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter @Setter
public class ApplicationForm {
    private String companyName;
    private String position;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate appliedDate;
    private ApplicationStage stage;
    private ApplicationResult result;
    private String memo;
    private boolean publicVisible;
}
