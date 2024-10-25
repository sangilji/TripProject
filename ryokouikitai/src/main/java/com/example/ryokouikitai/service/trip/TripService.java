package com.example.ryokouikitai.service.trip;

import com.example.ryokouikitai.Trip.DTO.CourseRequestDTO;
import com.example.ryokouikitai.Trip.Repository.CourseAttractionRepository;
import com.example.ryokouikitai.domain.area.*;
import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.dto.trip.AttractionDto;
import com.example.ryokouikitai.repository.area.AreaRepository;
import com.example.ryokouikitai.repository.area.ThemeRepository;
import com.example.ryokouikitai.repository.course.CourseRepository;
import com.example.ryokouikitai.repository.member.MemberRepository;
import com.example.ryokouikitai.repository.trip.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {
    private final AttractionRepository attractionRepository;
    private final AreaRepository areaRepository;
    private final ThemeRepository categoryRepository;
    private final MemberRepository memberRepository; // MemberRepository 주입
    private final CourseRepository courseRepository;
    private final CourseAttractionRepository courseAttractionRepository;

    @Transactional
    public Course createCourseWithAttractions(CourseRequestDTO courseRequestDTO, Integer memberId) {

        // Member 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        // Course 생성
        Course course = Course.builder()
                .title(courseRequestDTO.getTitle())
                .content(courseRequestDTO.getContent())
                .startAt(courseRequestDTO.getStartAt())
                .endAt(courseRequestDTO.getEndAt())
                .flag(courseRequestDTO.getFlag())
                .member(member) // 조회한 Member 설정
                .createdAt(LocalDateTime.now()) // 생성 시간
                .likeCount(0) // 좋아요 수 기본값
                .viewCount(0) // 조회수 기본값
                .build();


        course.setAttractions(courseRequestDTO.getAttractions().stream()
                .map(courseAttractionDTO -> courseAttractionDTO.getAttraction().stream()
                        .map(attractionCourseDto -> CourseAttraction.builder()
                                .attraction(attractionRepository.findById(attractionCourseDto.getId()).get()) // Attraction 엔티티 설정
                                .orders(attractionCourseDto.getOrder()) // 장소 순서 설정
                                .course(course) // Course와 연관 설정
                                .day(courseAttractionDTO.getDay()) // 각 장소에 해당하는 day 설정
                                .build())
                        .collect(Collectors.toList()))
                .collect(Collectors.toList()));

        // Course 저장
        courseRepository.save(course);

        return course;
    }

    // 추가: 어트랙션 조회 메서드
    public List<Attraction> getAttraction(String theme) {
        if (theme.isEmpty()) {
            // 테마가 "0"일 경우 모든 어트랙션 조회
            return attractionRepository.findAll();
        } else {
            // 테마에 해당하는 어트랙션 조회 (카테고리로 필터링)
            Category category = categoryRepository.findByName(theme)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            return attractionRepository.findByCategory(category);
        }
    }

    @Transactional
    public Attraction addAttraction(AttractionDto attractionDto) {
        Area area = areaRepository.findByName(attractionDto.getArea())
                .orElseThrow(() -> new IllegalArgumentException("Area not found"));
        Category category = categoryRepository.findByName(attractionDto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        Attraction attraction = attractionRepository.save(attractionDto.toAttraction(area, category));
        return attraction;
    }

    public List<Attraction> getAttractionByCourseAttraction(String theme) {
        if (theme.isEmpty()) {
            // 테마가 "0"일 경우 모든 어트랙션 조회
            return attractionRepository.findAllByCourseAttraction();
        }
            // 테마에 해당하는 어트랙션 조회 (카테고리로 필터링)
            Category category = categoryRepository.findByName(theme)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            return attractionRepository.findAllByCourseAttractionWithCategory(category);
    }

//    @Transactional(readOnly = true)
//    public CourseResponseDTO getCourseWithAttractions(Long courseId) {
//        Course course = courseRepository.findById(courseId)
//                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
//
//        // Course 엔티티를 DTO로 변환
//        CourseResponseDTO courseResponseDTO = new CourseResponseDTO(course);
//        return courseResponseDTO;
//    }
}
