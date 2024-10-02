package com.example.ryokouikitai.repository.area;

import com.example.ryokouikitai.domain.area.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Integer> {
    Optional<Area> findByName(String name);
}
