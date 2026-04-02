package com.devstudy.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_progress")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

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
