package com.devstudy.controller;

import com.devstudy.config.SessionConst;
import com.devstudy.domain.Member;
import com.devstudy.domain.StudyGroup;
import com.devstudy.dto.GroupCreateForm;
import com.devstudy.dto.LoginForm;
import com.devstudy.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginForm(HttpSession session, Model model) {
        if (session.getAttribute(SessionConst.MEMBER_ID) != null) {
            return "redirect:/group";
        }
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, HttpSession session, RedirectAttributes ra) {
        try {
            Member member = authService.login(form.getNickname().trim(), form.getGroupCode().trim().toUpperCase());
            session.setAttribute(SessionConst.MEMBER_ID, member.getId());
            session.setAttribute(SessionConst.NICKNAME, member.getNickname());
            session.setAttribute(SessionConst.GROUP_ID, member.getGroup().getId());
            session.setAttribute(SessionConst.GROUP_NAME, member.getGroup().getName());
            return "redirect:/group";
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/group/new")
    public String createGroupForm(Model model) {
        model.addAttribute("groupCreateForm", new GroupCreateForm());
        return "group-create";
    }

    @PostMapping("/group/new")
    public String createGroup(@ModelAttribute GroupCreateForm form, HttpSession session, RedirectAttributes ra) {
        if (form.getGroupName() == null || form.getGroupName().isBlank()
                || form.getNickname() == null || form.getNickname().isBlank()) {
            ra.addFlashAttribute("errorMsg", "그룹명과 닉네임을 입력해주세요.");
            return "redirect:/group/new";
        }
        StudyGroup group = authService.createGroup(form.getGroupName().trim(), form.getNickname().trim());
        // auto-login as creator
        Member member = group.getMembers().isEmpty()
                ? authService.login(form.getNickname().trim(), group.getGroupCode())
                : group.getMembers().get(0);
        session.setAttribute(SessionConst.MEMBER_ID, member.getId());
        session.setAttribute(SessionConst.NICKNAME, member.getNickname());
        session.setAttribute(SessionConst.GROUP_ID, group.getId());
        session.setAttribute(SessionConst.GROUP_NAME, group.getName());
        ra.addFlashAttribute("newGroupCode", group.getGroupCode());
        return "redirect:/group";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
