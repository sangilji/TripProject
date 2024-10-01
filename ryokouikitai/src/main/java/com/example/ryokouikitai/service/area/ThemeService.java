package com.example.ryokouikitai.service.area;

import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.repository.area.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;

    public List<Category> getTheme() {
        List<Category> theme = themeRepository.findAll();
        return theme;
    }
}
