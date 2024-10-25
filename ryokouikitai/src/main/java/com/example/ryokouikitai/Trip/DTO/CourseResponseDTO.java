package com.example.ryokouikitai.Trip.DTO;

import com.example.ryokouikitai.domain.area.Course;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDTO {
    private String title;
    private String content;
    private LocalDate startAt;
    private LocalDate endAt;
    private List<CourseAttractionDTO> attractions;

//    // Course 엔티티를 받아서 DTO로 변환하는 빌더 메서드
//    public static CourseResponseDTO fromCourse(Course course) {
//        return CourseResponseDTO.builder()
//                .title(course.getTitle())
//                .content(course.getContent())
//                .startAt(course.getStartAt())
//                .endAt(course.getEndAt())
//                .attractions(course.getCourseAttractions().stream()
//                        .map(CourseAttractionDTO::new)
//                        .collect(Collectors.toList()))
//                .build();
//    }

}
