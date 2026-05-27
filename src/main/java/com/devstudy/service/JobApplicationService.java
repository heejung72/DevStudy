package com.devstudy.service;

import com.devstudy.domain.JobApplication;
import com.devstudy.domain.Member;
import com.devstudy.dto.ApplicationForm;
import com.devstudy.repository.JobApplicationRepository;
import com.devstudy.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<JobApplication> getGroupApplications(Long groupId) {
        return jobApplicationRepository.findByGroupId(groupId);
    }

    @Transactional(readOnly = true)
    public List<JobApplication> getMyApplications(Long memberId) {
        return jobApplicationRepository.findByMemberIdOrderByAppliedDateDesc(memberId);
    }

    @Transactional(readOnly = true)
    public JobApplication getById(Long id) {
        return jobApplicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("지원 정보를 찾을 수 없습니다."));
    }

    @Transactional
    public JobApplication create(Long memberId, ApplicationForm form) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾을 수 없습니다."));
        LocalDateTime now = LocalDateTime.now();
        return jobApplicationRepository.save(JobApplication.builder()
                .member(member)
                .companyName(form.getCompanyName())
                .position(form.getPosition())
                .appliedDate(form.getAppliedDate())
                .stage(form.getStage())
                .result(form.getResult())
                .memo(form.getMemo())
                .publicVisible(form.isPublicVisible())
                .createdAt(now)
                .updatedAt(now)
                .build());
    }

    @Transactional
    public JobApplication update(Long id, Long memberId, ApplicationForm form) {
        JobApplication app = getById(id);
        if (!app.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        app.setCompanyName(form.getCompanyName());
        app.setPosition(form.getPosition());
        app.setAppliedDate(form.getAppliedDate());
        app.setStage(form.getStage());
        app.setResult(form.getResult());
        app.setMemo(form.getMemo());
        app.setPublicVisible(form.isPublicVisible());
        app.setUpdatedAt(LocalDateTime.now());
        return jobApplicationRepository.save(app);
    }

    @Transactional
    public void delete(Long id, Long memberId) {
        JobApplication app = getById(id);
        if (!app.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
        jobApplicationRepository.delete(app);
    }
}
