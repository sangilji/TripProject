package com.example.ryokouikitai.Trip.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseRequestDTO {
    private String title;
    private String content;
    private LocalDate  startAt;
    private LocalDate endAt;
    private Boolean flag;
    private List<CourseAttractionDTO> attractions; // 장소 목록 추가
    private Integer memberid;


    public Integer getMemberid() {
        return memberid;
    }

    public void setMember_id(Integer memberid) {
        this.memberid = memberid;
    }
}