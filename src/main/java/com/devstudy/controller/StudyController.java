package com.devstudy.controller;

import com.devstudy.config.SessionConst;
import com.devstudy.domain.StudyCategory;
import com.devstudy.dto.StudyCategoryForm;
import com.devstudy.service.LessonService;
import com.devstudy.service.StudyCategoryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class StudyController {

    private final StudyCategoryService studyCategoryService;
    private final LessonService lessonService;

    @GetMapping("/study")
    public String studyCategories(Model model) {
        model.addAttribute("categories", studyCategoryService.getAllCategories());
        model.addAttribute("categoryForm", new StudyCategoryForm());
        model.addAttribute("activePage", "study");
        return "study";
    }

    @PostMapping("/study/new")
    public String createCategory(@ModelAttribute StudyCategoryForm form, RedirectAttributes ra) {
        if (form.getName() == null || form.getName().isBlank()) {
            ra.addFlashAttribute("errorMsg", "스터디명을 입력해주세요.");
            return "redirect:/study";
        }
        studyCategoryService.create(form);
        ra.addFlashAttribute("successMsg", "'" + form.getName() + "' 스터디가 추가되었습니다!");
        return "redirect:/study";
    }

    @GetMapping("/study/{categoryId}/lessons")
    public String lessonList(@PathVariable Long categoryId, HttpSession session, Model model) {
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        StudyCategory category = studyCategoryService.getById(categoryId);
        model.addAttribute("category", category);
        model.addAttribute("lessons", lessonService.getLessonsByStudyCategory(category));
        model.addAttribute("activePage", "study");
        return "lessons";
    }
}
