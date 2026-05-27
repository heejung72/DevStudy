package com.devstudy.repository;

import com.devstudy.domain.Lesson;
import com.devstudy.domain.StudyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByOrderByOrderNumAsc();
    List<Lesson> findByStudyCategoryOrderByOrderNumAsc(StudyCategory studyCategory);
}
