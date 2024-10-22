package com.example.ryokouikitai.Trip;


import com.example.ryokouikitai.Trip.DTO.CourseRequestDTO;
import com.example.ryokouikitai.domain.member.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/trip")
@RequiredArgsConstructor
public class CourseController {


    private final CourseService courseService; // CourseService 주입

    // 새로운 코스 및 장소별 일정을 저장하는 엔드포인트
    @PostMapping("/saveSchedule")
    public ResponseEntity<String> saveSchedule(HttpSession session,CourseRequestDTO requestDTO) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        System.out.println("Received startAt: " + requestDTO.getStartAt());
        System.out.println("Received endAt: " + requestDTO.getEndAt());
        courseService.createCourseWithAttractions(requestDTO,memberInfo.getId());
        return ResponseEntity.ok("Schedule saved successfully");
    }

    // 기존 코스 업데이트하는 엔드포인트 (필요 시 수정)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Integer id, @RequestBody CourseRequestDTO requestDTO) {
        courseService.updateCourse(id, requestDTO);
        return ResponseEntity.ok("Course updated successfully");
    }

    // 일정 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
}

