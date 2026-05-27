package com.devstudy.repository;

import com.devstudy.domain.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByMemberIdOrderByAppliedDateDesc(Long memberId);

    @Query("SELECT a FROM JobApplication a WHERE a.member.group.id = :groupId ORDER BY a.appliedDate DESC, a.createdAt DESC")
    List<JobApplication> findByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT a FROM JobApplication a WHERE a.member.group.id = :groupId AND a.appliedDate >= :since ORDER BY a.appliedDate DESC")
    List<JobApplication> findRecentByGroupId(@Param("groupId") Long groupId, @Param("since") LocalDate since);
}
