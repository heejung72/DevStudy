package com.devstudy.controller;

import com.devstudy.config.SessionConst;
import com.devstudy.domain.Lesson;
import com.devstudy.service.LessonService;
import com.devstudy.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;
    private final QuizService quizService;

    @GetMapping("/{id}")
    public String lessonDetail(@PathVariable Long id, HttpSession session, Model model) {
        Lesson lesson = lessonService.getLessonById(id)
                .orElseThrow(() -> new IllegalArgumentException("레슨을 찾을 수 없습니다: " + id));

        model.addAttribute("lesson", lesson);
        model.addAttribute("questions", quizService.getQuestionsByLessonId(id));
        model.addAttribute("prevLesson", lessonService.getPrevLesson(id).orElse(null));
        model.addAttribute("nextLesson", lessonService.getNextLesson(id).orElse(null));
        model.addAttribute("activePage", "study");
        return "lesson-detail";
    }
}
