package com.example.ryokouikitai.service.member;

import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.dto.member.JoinForm;
import com.example.ryokouikitai.dto.member.LoginForm;
import com.example.ryokouikitai.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

    public Member login(LoginForm loginForm) {
        Optional<Member> member = memberRepository.findByUserIdAndPassword(loginForm.getId(), loginForm.getPassword());
        if (!member.isPresent()) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        return member.get();
    }

    @Transactional
    public void updateProfile(Integer id, String profile) {
        Member member = memberRepository.getReferenceById(id);
        member.updateProfile(profile);
    }
}
