package com.example.ryokouikitai.repository.accompany;

import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.domain.accompany.AccompanyComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccompanyCommentRepository  extends JpaRepository<AccompanyComment, Integer> {
    List<AccompanyComment> findAllByAccompanyId(Integer postId);
    void deleteByAccompany(Accompany accompany);
}
