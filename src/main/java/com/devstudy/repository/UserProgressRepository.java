package com.devstudy.repository;

import com.devstudy.domain.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    List<UserProgress> findBySessionIdOrderByCompletedAtDesc(String sessionId);
    Optional<UserProgress> findBySessionIdAndLessonId(String sessionId, Long lessonId);
    long countBySessionIdAndCompleted(String sessionId, boolean completed);
}
