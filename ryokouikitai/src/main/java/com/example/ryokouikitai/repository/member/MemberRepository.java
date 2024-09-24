package com.example.ryokouikitai.repository.member;

import com.example.ryokouikitai.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Integer> {
    boolean existsByUserId(String userId);

    boolean existsByNickname(String email);
}
