package com.example.ryokouikitai.dto.trip;


import com.example.ryokouikitai.domain.area.Area;
import com.example.ryokouikitai.domain.area.Attraction;
import com.example.ryokouikitai.domain.area.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class AttractionDto {
    private String address;
    private String area;
    private String category;
    private String latitude;
    private String longitude;
    private String content;
    private String name;

    public Attraction toAttraction(Area area, Category category) {
        return Attraction.builder()
                .address(address)
                .area(area)
                .category(category)
                .url("")
                .content(content)
                .latitude(latitude)
                .longitude(longitude)
                .name(name)
                .build();


    }
}
