package com.example.ryokouikitai.Trip.Repository;


import com.example.ryokouikitai.domain.area.Course;
import com.example.ryokouikitai.domain.area.CourseAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseAttractionRepository extends JpaRepository<CourseAttraction, Integer> {
    void deleteByCourseId(Integer courseId); // 코스 ID로 장소들을 삭제

    List<CourseAttraction> findByCourseOrderByDayAscOrdersAsc(Course course);
}
