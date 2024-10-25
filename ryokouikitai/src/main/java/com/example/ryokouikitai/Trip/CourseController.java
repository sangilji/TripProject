package com.example.ryokouikitai.Trip;


import com.example.ryokouikitai.Trip.DTO.CourseRequestDTO;
import com.example.ryokouikitai.Trip.DTO.CourseResponseDTO;
import com.example.ryokouikitai.domain.member.MemberInfo;
import com.example.ryokouikitai.global.response.BaseResponse;
import com.example.ryokouikitai.service.course.CourseService;
import com.example.ryokouikitai.service.trip.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/trip")
@RequiredArgsConstructor
public class CourseController {


    private final CourseService courseService; // CourseService 주입
    private final TripService tripService; // CourseService 주입

    // 새로운 코스 및 장소별 일정을 저장하는 엔드포인트
    @PostMapping("/saveSchedule")
    public ResponseEntity<?> saveSchedule(HttpSession session,@RequestBody CourseRequestDTO courseRequestDTO) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        System.out.println("Received title: " + courseRequestDTO.getTitle());
        System.out.println("Received startAt: " + courseRequestDTO.getStartAt());
        System.out.println("Received endAt: " + courseRequestDTO.getEndAt());
        tripService.createCourseWithAttractions(courseRequestDTO,memberInfo.getId());
        return BaseResponse.okWithData(HttpStatus.OK,"Schedule saved successfully",courseRequestDTO);
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
    // 일정 조회 API 추가
//    @GetMapping("/course/{courseId}")
//    public ResponseEntity<?> getCourseDetails(@PathVariable Long courseId) {
//        CourseResponseDTO courseResponseDTO = tripService.getCourseWithAttractions(courseId);
//        return ResponseEntity.ok(courseResponseDTO); // 코스 상세 정보를 JSON 형태로 반환
//    }

}

