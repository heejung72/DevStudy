package com.devstudy.controller;

import com.devstudy.config.SessionConst;
import com.devstudy.domain.Member;
import com.devstudy.repository.MemberRepository;
import com.devstudy.service.GoalService;
import com.devstudy.service.JobApplicationService;
import com.devstudy.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MypageController {

    private final MemberRepository memberRepository;
    private final JobApplicationService jobApplicationService;
    private final QuizService quizService;
    private final GoalService goalService;

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        Long groupId  = (Long) session.getAttribute(SessionConst.GROUP_ID);

        Member member = memberRepository.findById(memberId).orElseThrow();

        model.addAttribute("member", member);
        model.addAttribute("myApplications", jobApplicationService.getMyApplications(memberId));
        model.addAttribute("myProgress", quizService.getProgressByMember(memberId));
        model.addAttribute("completedCount", quizService.getCompletedCount(memberId));
        model.addAttribute("myGoal", goalService.getCurrentGoal(memberId).orElse(null));
        model.addAttribute("recentCheckins", goalService.getGroupRecentCheckins(groupId, 30)
                .stream().filter(c -> c.getMember().getId().equals(memberId)).toList());
        model.addAttribute("activePage", "mypage");
        return "mypage";
    }
}
