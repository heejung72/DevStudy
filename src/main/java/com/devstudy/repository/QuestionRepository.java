package com.devstudy.repository;

import com.devstudy.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByLessonIdOrderByOrderNumAsc(Long lessonId);
}
