package com.devstudy.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_progress",
       uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "lesson_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "lesson_id")
    private Long lessonId;

    @Column(name = "lesson_title")
    private String lessonTitle;

    @Column
    private boolean completed;

    @Column
    private int score;

    @Column(name = "total_questions")
    private int totalQuestions;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
