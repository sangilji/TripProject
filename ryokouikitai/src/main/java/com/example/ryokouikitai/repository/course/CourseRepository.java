package com.example.ryokouikitai.repository.course;

import com.example.ryokouikitai.domain.area.Course;
import com.example.ryokouikitai.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Page<Course> findByMemberOrderByIdAsc(Member member, Pageable pageable);
}
