package com.example.ryokouikitai.repository.accompany;

import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.dto.accompany.AccompanyResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

public interface AccompanyRepository extends JpaRepository<Accompany, Integer> {

    @Query("select new com.example.ryokouikitai.dto.accompany.AccompanyResponseDto(a, count(c)) " +
            "from Accompany a left join AccompanyComment c on c.accompany = a " +
            "group by a")
    Page<AccompanyResponseDto> getAccompanyWithMemberAndCommentCount(Pageable pageable);

    @Query("select a from Accompany a left join fetch a.accompanyComments where a.id = :id")
    Accompany findByIdWithComment(@Param("id") Integer id);


    @Query("select new com.example.ryokouikitai.dto.accompany.AccompanyResponseDto(a, count(c)) " +
            "from Accompany a left join AccompanyComment c on c.accompany = a where a.category.name like %:theme% and a.member.id = :id and a.title like %:title% " +
            "group by a")
    Page<AccompanyResponseDto> getAccompanyByThemeWithMemberAndCommentCount(@Param("theme") String theme,@Param("id") int id , @Param("title") String title, Pageable pageable);

    @Query("select new com.example.ryokouikitai.dto.accompany.AccompanyResponseDto(a, count(c)) " +
            "from Accompany a left join AccompanyComment c on c.accompany = a where a.member.id= :id " +
            "group by a")
    Page<AccompanyResponseDto> getAccompanyByMemberIdWithMemberAndCommentCount(@Param("id") int id, Pageable pageable);

    @Query("select new com.example.ryokouikitai.dto.accompany.AccompanyResponseDto(a, count(c)) " +
            "from Accompany a left join AccompanyComment c on c.accompany = a where a.title like %:title% and a.category.name like %:theme% " +
            "group by a")
    Page<AccompanyResponseDto> getAccompanyByTitleWithMemberAndCommentCount(@Param("title") String title, @Param("theme")String theme, Pageable pageable);
}