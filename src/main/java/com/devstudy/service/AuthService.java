package com.devstudy.service;

import com.devstudy.domain.Member;
import com.devstudy.domain.StudyGroup;
import com.devstudy.repository.MemberRepository;
import com.devstudy.repository.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudyGroupRepository studyGroupRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public StudyGroup createGroup(String groupName, String nickname) {
        String code = generateUniqueCode();
        StudyGroup group = studyGroupRepository.save(StudyGroup.builder()
                .name(groupName)
                .groupCode(code)
                .createdAt(LocalDateTime.now())
                .build());

        memberRepository.save(Member.builder()
                .nickname(nickname)
                .group(group)
                .joinedAt(LocalDateTime.now())
                .build());

        return group;
    }

    @Transactional
    public Member login(String nickname, String groupCode) {
        StudyGroup group = studyGroupRepository.findByGroupCode(groupCode)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹 코드입니다: " + groupCode));

        return memberRepository.findByNicknameAndGroup(nickname, group)
                .orElseGet(() -> memberRepository.save(Member.builder()
                        .nickname(nickname)
                        .group(group)
                        .joinedAt(LocalDateTime.now())
                        .build()));
    }

    private String generateUniqueCode() {
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        Random random = new Random();
        String code;
        do {
            StringBuilder sb = new StringBuilder(6);
            for (int i = 0; i < 6; i++) {
                sb.append(chars.charAt(random.nextInt(chars.length())));
            }
            code = sb.toString();
        } while (studyGroupRepository.existsByGroupCode(code));
        return code;
    }
}
