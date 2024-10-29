package com.example.ryokouikitai.domain.area;

import com.example.ryokouikitai.dto.trip.AttractionCourseDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder

public class CourseAttraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attraction_id")
    private Attraction attraction;

    @NotNull
    private int day;
    @NotNull
    private int orders;


    public AttractionCourseDto toAttractionCourseDto() {
        return AttractionCourseDto.builder()
                .address(attraction.getAddress())
                .id(attraction.getId())
                .order(orders)
                .name(attraction.getName())
                .content(attraction.getContent())
                .url(attraction.getUrl())
                .latitude(attraction.getLatitude())
                .longitude(attraction.getLongitude())
                .category(attraction.getCategory())
                .area(attraction.getArea())
                .build();
    }
}
