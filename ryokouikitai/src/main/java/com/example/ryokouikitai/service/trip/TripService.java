package com.example.ryokouikitai.service.trip;

import com.example.ryokouikitai.domain.area.Area;
import com.example.ryokouikitai.domain.area.Attraction;
import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.dto.trip.AttractionDto;
import com.example.ryokouikitai.repository.area.AreaRepository;
import com.example.ryokouikitai.repository.area.ThemeRepository;
import com.example.ryokouikitai.repository.trip.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    private final AttractionRepository attractionRepository;
    private final AreaRepository areaRepository;
    private final ThemeRepository categoryRepository;
    public List<Attraction> getAttraction(String theme) {
        if (theme.equals("0")) {
            List<Attraction> attractions = attractionRepository.findAll();
            return attractions;
        }
        return null;
    }

    @Transactional
    public Attraction addAttraction(AttractionDto attractionDto) {
        Area area = areaRepository.findByName(attractionDto.getArea())
                .orElseThrow(() -> new IllegalArgumentException("없는 지역"));
        Category category = categoryRepository.findByName(attractionDto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("없는 카테고리"));
        Attraction attraction = attractionRepository.save(attractionDto.toAttraction(area, category));
        return attraction;
    }
}
