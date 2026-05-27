package com.devstudy.repository;

import com.devstudy.domain.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {
    Optional<StudyGroup> findByGroupCode(String groupCode);
    boolean existsByGroupCode(String groupCode);
}
