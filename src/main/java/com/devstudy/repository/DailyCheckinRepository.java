package com.devstudy.repository;

import com.devstudy.domain.DailyCheckin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyCheckinRepository extends JpaRepository<DailyCheckin, Long> {

    Optional<DailyCheckin> findByMemberIdAndCheckinDate(Long memberId, LocalDate checkinDate);

    @Query("SELECT c FROM DailyCheckin c WHERE c.member.group.id = :groupId AND c.checkinDate = :date ORDER BY c.createdAt ASC")
    List<DailyCheckin> findByGroupIdAndDate(@Param("groupId") Long groupId, @Param("date") LocalDate date);

    @Query("SELECT c FROM DailyCheckin c WHERE c.member.group.id = :groupId AND c.checkinDate >= :since ORDER BY c.checkinDate DESC, c.createdAt DESC")
    List<DailyCheckin> findRecentByGroupId(@Param("groupId") Long groupId, @Param("since") LocalDate since);
}
