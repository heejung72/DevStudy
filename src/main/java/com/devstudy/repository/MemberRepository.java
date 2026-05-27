package com.devstudy.repository;

import com.devstudy.domain.Member;
import com.devstudy.domain.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNicknameAndGroup(String nickname, StudyGroup group);
    List<Member> findByGroup(StudyGroup group);
    long countByGroup(StudyGroup group);
}
