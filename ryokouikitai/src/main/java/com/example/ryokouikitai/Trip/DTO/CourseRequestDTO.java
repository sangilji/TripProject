package com.example.ryokouikitai.Trip.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseRequestDTO {
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  startAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endAt;
    private Boolean flag;
    private List<CourseAttractionDTO> attractions; // 장소 목록 추가

}