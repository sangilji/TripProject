package com.example.ryokouikitai.Trip.DTO;


import com.example.ryokouikitai.domain.area.Attraction;
import com.example.ryokouikitai.dto.trip.AttractionCourseDto;
import com.example.ryokouikitai.dto.trip.AttractionDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CourseAttractionDTO {
    private Integer day;
    private List<AttractionCourseDto> attraction;

}
