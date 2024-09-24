package com.example.ryokouikitai.service.member;

import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.dto.member.JoinForm;
import com.example.ryokouikitai.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public boolean existId(String userId) {
        return memberRepository.existsByUserId(userId);
    }

    public boolean existNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public void join(JoinForm joinForm) {
        Member member = Member.builder()
                .userId(joinForm.getId())
                .password(joinForm.getPassword())
                .nickname(joinForm.getNickname())
                .email(joinForm.getEmail())
                .theme(joinForm.getTheme())
                .build();
        memberRepository.save(member);
    }
}
