package com.example.ryokouikitai.repository.area;

import com.example.ryokouikitai.domain.area.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Category, Integer> {
}
