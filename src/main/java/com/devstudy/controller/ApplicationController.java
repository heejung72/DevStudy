package com.devstudy.controller;

import com.devstudy.config.SessionConst;
import com.devstudy.domain.ApplicationResult;
import com.devstudy.domain.ApplicationStage;
import com.devstudy.domain.JobApplication;
import com.devstudy.dto.ApplicationForm;
import com.devstudy.service.JobApplicationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping
    public String list(HttpSession session, Model model) {
        Long groupId = (Long) session.getAttribute(SessionConst.GROUP_ID);
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);

        model.addAttribute("applications", jobApplicationService.getGroupApplications(groupId));
        model.addAttribute("stages", ApplicationStage.values());
        model.addAttribute("results", ApplicationResult.values());
        model.addAttribute("currentMemberId", memberId);
        model.addAttribute("activePage", "applications");
        return "applications";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        ApplicationForm form = new ApplicationForm();
        form.setAppliedDate(LocalDate.now());
        form.setStage(ApplicationStage.DOCUMENT);
        form.setResult(ApplicationResult.IN_PROGRESS);
        form.setPublicVisible(true);
        model.addAttribute("form", form);
        model.addAttribute("stages", ApplicationStage.values());
        model.addAttribute("results", ApplicationResult.values());
        model.addAttribute("activePage", "applications");
        return "application-form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute ApplicationForm form, HttpSession session, RedirectAttributes ra) {
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        jobApplicationService.create(memberId, form);
        ra.addFlashAttribute("successMsg", "지원 정보가 등록되었습니다.");
        return "redirect:/applications";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, HttpSession session, Model model) {
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        JobApplication app = jobApplicationService.getById(id);
        if (!app.getMember().getId().equals(memberId)) {
            return "redirect:/applications";
        }
        ApplicationForm form = new ApplicationForm();
        form.setCompanyName(app.getCompanyName());
        form.setPosition(app.getPosition());
        form.setAppliedDate(app.getAppliedDate());
        form.setStage(app.getStage());
        form.setResult(app.getResult());
        form.setMemo(app.getMemo());
        form.setPublicVisible(app.isPublicVisible());
        model.addAttribute("form", form);
        model.addAttribute("appId", id);
        model.addAttribute("stages", ApplicationStage.values());
        model.addAttribute("results", ApplicationResult.values());
        model.addAttribute("activePage", "applications");
        return "application-form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute ApplicationForm form,
                         HttpSession session, RedirectAttributes ra) {
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        jobApplicationService.update(id, memberId, form);
        ra.addFlashAttribute("successMsg", "지원 정보가 수정되었습니다.");
        return "redirect:/applications";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, HttpSession session, RedirectAttributes ra) {
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        jobApplicationService.delete(id, memberId);
        ra.addFlashAttribute("successMsg", "삭제되었습니다.");
        return "redirect:/applications";
    }
}
