package com.devstudy.controller;

import com.devstudy.service.LessonService;
import com.devstudy.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LessonService lessonService;
    private final QuizService quizService;

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        ensureSessionId(session);
        String sessionId = (String) session.getAttribute("sessionId");

        model.addAttribute("lessons", lessonService.getAllLessons());
        model.addAttribute("completedCount", quizService.getCompletedCount(sessionId));
        model.addAttribute("totalCount", lessonService.getAllLessons().size());
        model.addAttribute("progressList", quizService.getProgressBySession(sessionId));
        return "index";
    }

    @GetMapping("/progress")
    public String progress(HttpSession session, Model model) {
        ensureSessionId(session);
        String sessionId = (String) session.getAttribute("sessionId");

        model.addAttribute("lessons", lessonService.getAllLessons());
        model.addAttribute("progressList", quizService.getProgressBySession(sessionId));
        model.addAttribute("completedCount", quizService.getCompletedCount(sessionId));
        model.addAttribute("totalCount", lessonService.getAllLessons().size());
        return "progress";
    }

    private void ensureSessionId(HttpSession session) {
        if (session.getAttribute("sessionId") == null) {
            session.setAttribute("sessionId", UUID.randomUUID().toString());
        }
    }
}
