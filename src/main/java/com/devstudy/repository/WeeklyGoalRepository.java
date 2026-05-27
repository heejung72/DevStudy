package com.devstudy.repository;

import com.devstudy.domain.WeeklyGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeeklyGoalRepository extends JpaRepository<WeeklyGoal, Long> {

    Optional<WeeklyGoal> findByMemberIdAndWeekStart(Long memberId, LocalDate weekStart);

    @Query("SELECT g FROM WeeklyGoal g WHERE g.member.group.id = :groupId AND g.weekStart = :weekStart ORDER BY g.createdAt ASC")
    List<WeeklyGoal> findByGroupIdAndWeekStart(@Param("groupId") Long groupId, @Param("weekStart") LocalDate weekStart);
}
