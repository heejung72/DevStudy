package com.devstudy.controller;

import com.devstudy.config.SessionConst;
import com.devstudy.service.GroupService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping({"/", "/group"})
    public String dashboard(HttpSession session, Model model) {
        Long groupId = (Long) session.getAttribute(SessionConst.GROUP_ID);
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);

        model.addAttribute("group", groupService.getGroup(groupId));
        model.addAttribute("members", groupService.getMembers(groupId));
        model.addAttribute("todayCheckins", groupService.getTodayCheckins(groupId));
        model.addAttribute("recentApplications", groupService.getRecentApplications(groupId));
        model.addAttribute("weekGoals", groupService.getThisWeekGoals(groupId));
        model.addAttribute("currentMemberId", memberId);
        model.addAttribute("activePage", "group");
        return "group-dashboard";
    }
}
