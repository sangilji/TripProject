package com.example.ryokouikitai.repository.trip;


import com.example.ryokouikitai.domain.area.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Integer> {

}
