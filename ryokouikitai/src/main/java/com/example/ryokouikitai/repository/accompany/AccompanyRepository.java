package com.example.ryokouikitai.repository.accompany;

import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.dto.accompany.AccompanyResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccompanyRepository extends JpaRepository<Accompany, Integer> {

    @Query("select new com.example.ryokouikitai.dto.accompany.AccompanyResponseDto(a, count(c)) " +
            "from Accompany a left join AccompanyComment c on c.accompany = a " +
            "group by a")
    Page<AccompanyResponseDto> getAccompanyWithMemberAndCommentCount(Pageable pageable);
}
