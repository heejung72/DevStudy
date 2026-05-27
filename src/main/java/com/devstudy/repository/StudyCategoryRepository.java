package com.devstudy.repository;

import com.devstudy.domain.StudyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudyCategoryRepository extends JpaRepository<StudyCategory, Long> {
    Optional<StudyCategory> findByName(String name);
    List<StudyCategory> findAllByOrderByOrderNumAsc();
}
