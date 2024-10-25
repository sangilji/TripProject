package com.example.ryokouikitai.dto.trip;

import com.example.ryokouikitai.domain.area.Area;
import com.example.ryokouikitai.domain.area.Category;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class AttractionCourseDto {
    private Integer id;
    private Integer order;
    private String name;
    private String address;
    private String content;
    private String url;
    private String latitude;
    private String longitude;

    private Category category;

    private Area area;
}
