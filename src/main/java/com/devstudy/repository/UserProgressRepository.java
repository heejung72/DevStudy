package com.devstudy.repository;

import com.devstudy.domain.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    Optional<UserProgress> findByMemberIdAndLessonId(Long memberId, Long lessonId);
    List<UserProgress> findByMemberIdOrderByCompletedAtDesc(Long memberId);
    long countByMemberIdAndCompleted(Long memberId, boolean completed);
}
