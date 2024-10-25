package com.example.ryokouikitai.repository.trip;


import com.example.ryokouikitai.domain.area.Attraction;
import com.example.ryokouikitai.domain.area.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Integer> {

    List<Attraction> findByCategory(Category theme);

    @Query("select a from CourseAttraction ca inner join ca.attraction a")
    List<Attraction> findAllByCourseAttraction();

    @Query("select a from CourseAttraction ca inner join ca.attraction a where ca.attraction.category= :category")
    List<Attraction> findAllByCourseAttractionWithCategory(@Param("category") Category category);
}
