package com.example.ryokouikitai.repository.area;

import com.example.ryokouikitai.domain.area.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);
}
