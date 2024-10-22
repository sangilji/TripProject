package com.example.ryokouikitai.Trip.Repository;

import com.example.ryokouikitai.domain.area.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // Schedule 엔티티와 연관된 DB 작업을 처리
}
