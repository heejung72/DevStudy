package com.devstudy.service;

import com.devstudy.domain.DailyCheckin;
import com.devstudy.domain.Member;
import com.devstudy.domain.WeeklyGoal;
import com.devstudy.dto.CheckinForm;
import com.devstudy.dto.GoalForm;
import com.devstudy.repository.DailyCheckinRepository;
import com.devstudy.repository.MemberRepository;
import com.devstudy.repository.WeeklyGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final WeeklyGoalRepository weeklyGoalRepository;
    private final DailyCheckinRepository dailyCheckinRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Optional<WeeklyGoal> getCurrentGoal(Long memberId) {
        LocalDate weekStart = LocalDate.now().with(DayOfWeek.MONDAY);
        return weeklyGoalRepository.findByMemberIdAndWeekStart(memberId, weekStart);
    }

    @Transactional
    public WeeklyGoal saveGoal(Long memberId, GoalForm form) {
        LocalDate weekStart = LocalDate.now().with(DayOfWeek.MONDAY);
        WeeklyGoal goal = weeklyGoalRepository.findByMemberIdAndWeekStart(memberId, weekStart)
                .orElseGet(() -> {
                    Member member = memberRepository.findById(memberId).orElseThrow();
                    return WeeklyGoal.builder()
                            .member(member)
                            .weekStart(weekStart)
                            .createdAt(LocalDateTime.now())
                            .build();
                });
        goal.setContent(form.getContent());
        return weeklyGoalRepository.save(goal);
    }

    @Transactional(readOnly = true)
    public Optional<DailyCheckin> getTodayCheckin(Long memberId) {
        return dailyCheckinRepository.findByMemberIdAndCheckinDate(memberId, LocalDate.now());
    }

    @Transactional
    public DailyCheckin checkin(Long memberId, CheckinForm form) {
        LocalDate today = LocalDate.now();
        DailyCheckin checkin = dailyCheckinRepository.findByMemberIdAndCheckinDate(memberId, today)
                .orElseGet(() -> {
                    Member member = memberRepository.findById(memberId).orElseThrow();
                    return DailyCheckin.builder()
                            .member(member)
                            .checkinDate(today)
                            .createdAt(LocalDateTime.now())
                            .build();
                });
        checkin.setContent(form.getContent());
        return dailyCheckinRepository.save(checkin);
    }

    @Transactional(readOnly = true)
    public List<DailyCheckin> getGroupRecentCheckins(Long groupId, int days) {
        return dailyCheckinRepository.findRecentByGroupId(groupId, LocalDate.now().minusDays(days));
    }

    @Transactional(readOnly = true)
    public List<WeeklyGoal> getGroupThisWeekGoals(Long groupId) {
        LocalDate weekStart = LocalDate.now().with(DayOfWeek.MONDAY);
        return weeklyGoalRepository.findByGroupIdAndWeekStart(groupId, weekStart);
    }
}
