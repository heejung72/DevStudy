package com.devstudy.service;

import com.devstudy.domain.*;
import com.devstudy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final StudyGroupRepository studyGroupRepository;
    private final MemberRepository memberRepository;
    private final DailyCheckinRepository dailyCheckinRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final WeeklyGoalRepository weeklyGoalRepository;

    @Transactional(readOnly = true)
    public StudyGroup getGroup(Long groupId) {
        return studyGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹을 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Member> getMembers(Long groupId) {
        StudyGroup group = getGroup(groupId);
        return memberRepository.findByGroup(group);
    }

    @Transactional(readOnly = true)
    public List<DailyCheckin> getTodayCheckins(Long groupId) {
        return dailyCheckinRepository.findByGroupIdAndDate(groupId, LocalDate.now());
    }

    @Transactional(readOnly = true)
    public List<JobApplication> getRecentApplications(Long groupId) {
        return jobApplicationRepository.findRecentByGroupId(groupId, LocalDate.now().minusDays(7));
    }

    @Transactional(readOnly = true)
    public List<WeeklyGoal> getThisWeekGoals(Long groupId) {
        LocalDate weekStart = LocalDate.now().with(DayOfWeek.MONDAY);
        return weeklyGoalRepository.findByGroupIdAndWeekStart(groupId, weekStart);
    }
}
