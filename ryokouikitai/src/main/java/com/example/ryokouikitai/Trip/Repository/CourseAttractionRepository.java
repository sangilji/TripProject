package com.example.ryokouikitai.Trip.Repository;


import com.example.ryokouikitai.domain.area.CourseAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseAttractionRepository extends JpaRepository<CourseAttraction, Integer> {
    void deleteByCourseId(Integer courseId); // 코스 ID로 장소들을 삭제
}
