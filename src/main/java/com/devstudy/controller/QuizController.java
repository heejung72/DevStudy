package com.devstudy.controller;

import com.devstudy.domain.Lesson;
import com.devstudy.service.LessonService;
import com.devstudy.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final LessonService lessonService;
    private final QuizService quizService;

    @GetMapping("/{lessonId}")
    public String quizPage(@PathVariable Long lessonId, Model model) {
        Lesson lesson = lessonService.getLessonById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("레슨을 찾을 수 없습니다: " + lessonId));
        model.addAttribute("lesson", lesson);
        model.addAttribute("questions", quizService.getQuestionsByLessonId(lessonId));
        return "quiz";
    }

    @PostMapping("/{lessonId}/submit")
    public String submitQuiz(@PathVariable Long lessonId,
                              @RequestParam Map<String, String> answers,
                              HttpSession session,
                              Model model) {
        ensureSessionId(session);
        String sessionId = (String) session.getAttribute("sessionId");

        Lesson lesson = lessonService.getLessonById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("레슨을 찾을 수 없습니다: " + lessonId));

        QuizService.QuizResult result = quizService.gradeQuiz(lessonId, lesson.getTitle(), sessionId, answers);

        model.addAttribute("lesson", lesson);
        model.addAttribute("result", result);
        model.addAttribute("nextLesson", lessonService.getNextLesson(lessonId).orElse(null));
        return "quiz-result";
    }

    private void ensureSessionId(HttpSession session) {
        if (session.getAttribute("sessionId") == null) {
            session.setAttribute("sessionId", UUID.randomUUID().toString());
        }
    }
}
