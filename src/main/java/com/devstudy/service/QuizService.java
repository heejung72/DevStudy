package com.devstudy.service;

import com.devstudy.domain.Question;
import com.devstudy.domain.UserProgress;
import com.devstudy.repository.QuestionRepository;
import com.devstudy.repository.UserProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuestionRepository questionRepository;
    private final UserProgressRepository userProgressRepository;

    public List<Question> getQuestionsByLessonId(Long lessonId) {
        return questionRepository.findByLessonIdOrderByOrderNumAsc(lessonId);
    }

    @Transactional
    public QuizResult gradeQuiz(Long lessonId, String lessonTitle, String sessionId, Map<String, String> answers) {
        List<Question> questions = getQuestionsByLessonId(lessonId);
        int correct = 0;

        for (Question q : questions) {
            String submitted = answers.get("q" + q.getId());
            if (q.getCorrectAnswer().equals(submitted)) {
                correct++;
            }
        }

        int total = questions.size();
        int score = total > 0 ? (correct * 100 / total) : 0;

        Optional<UserProgress> existing = userProgressRepository.findBySessionIdAndLessonId(sessionId, lessonId);
        if (existing.isPresent()) {
            UserProgress progress = existing.get();
            if (score > progress.getScore()) {
                progress.setScore(score);
                progress.setCompleted(score >= 60);
                progress.setCompletedAt(LocalDateTime.now());
                userProgressRepository.save(progress);
            }
        } else {
            userProgressRepository.save(UserProgress.builder()
                    .sessionId(sessionId)
                    .lessonId(lessonId)
                    .lessonTitle(lessonTitle)
                    .score(score)
                    .totalQuestions(total)
                    .completed(score >= 60)
                    .completedAt(LocalDateTime.now())
                    .build());
        }

        return new QuizResult(correct, total, score, questions, answers);
    }

    public List<UserProgress> getProgressBySession(String sessionId) {
        return userProgressRepository.findBySessionIdOrderByCompletedAtDesc(sessionId);
    }

    public long getCompletedCount(String sessionId) {
        return userProgressRepository.countBySessionIdAndCompleted(sessionId, true);
    }

    public record QuizResult(int correct, int total, int score,
                              List<Question> questions, Map<String, String> answers) {
        public boolean isPassed() { return score >= 60; }
    }
}
