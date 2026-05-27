package com.devstudy.controller;

import com.devstudy.config.SessionConst;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // "/" is handled by GroupController — this class is kept as a safety redirect
    @GetMapping("/progress")
    public String progress(HttpSession session) {
        if (session.getAttribute(SessionConst.MEMBER_ID) == null) {
            return "redirect:/login";
        }
        return "redirect:/mypage";
    }
}
