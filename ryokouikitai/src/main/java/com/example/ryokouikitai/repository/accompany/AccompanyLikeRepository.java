package com.example.ryokouikitai.repository.accompany;

import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.domain.accompany.AccompanyLike;
import com.example.ryokouikitai.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccompanyLikeRepository  extends JpaRepository<AccompanyLike, Integer> {

    Optional<AccompanyLike> findByMemberAndAccompany(Member member, Accompany accompany);
}
