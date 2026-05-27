package com.devstudy.controller;

import com.devstudy.config.SessionConst;
import com.devstudy.dto.CheckinForm;
import com.devstudy.dto.GoalForm;
import com.devstudy.service.GoalService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @GetMapping
    public String goals(HttpSession session, Model model) {
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        Long groupId  = (Long) session.getAttribute(SessionConst.GROUP_ID);

        model.addAttribute("myGoal", goalService.getCurrentGoal(memberId).orElse(null));
        model.addAttribute("myCheckin", goalService.getTodayCheckin(memberId).orElse(null));
        model.addAttribute("groupCheckins", goalService.getGroupRecentCheckins(groupId, 7));
        model.addAttribute("groupGoals", goalService.getGroupThisWeekGoals(groupId));
        model.addAttribute("goalForm", new GoalForm());
        model.addAttribute("checkinForm", new CheckinForm());
        model.addAttribute("activePage", "goals");
        return "goals";
    }

    @PostMapping("/save")
    public String saveGoal(@ModelAttribute GoalForm form, HttpSession session, RedirectAttributes ra) {
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        goalService.saveGoal(memberId, form);
        ra.addFlashAttribute("successMsg", "이번 주 목표가 저장되었습니다!");
        return "redirect:/goals";
    }

    @PostMapping("/checkin")
    public String checkin(@ModelAttribute CheckinForm form, HttpSession session, RedirectAttributes ra) {
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        goalService.checkin(memberId, form);
        ra.addFlashAttribute("successMsg", "오늘의 체크인 완료!");
        return "redirect:/goals";
    }
}
