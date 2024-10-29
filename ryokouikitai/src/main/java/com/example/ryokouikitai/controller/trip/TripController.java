package com.example.ryokouikitai.controller.trip;


import com.example.ryokouikitai.Trip.DTO.CourseRequestDTO;
import com.example.ryokouikitai.Trip.DTO.CourseResponseDTO;
import com.example.ryokouikitai.domain.area.Attraction;
import com.example.ryokouikitai.domain.area.Course;
import com.example.ryokouikitai.domain.member.MemberInfo;
import com.example.ryokouikitai.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/trip")
@Component
public class TripController {

    private final CourseService courseService;

    @GetMapping("/schedule")
    public String trip(Model model) {
        List<Attraction> attraction = courseService.getAttraction();
        model.addAttribute("attraction", attraction);
        return "trip/tokyoSchedule";
    }
    @Value("${ApiKey.MapApiKey}")
    private static String MapApiKey;

    public static void API() {
        System.out.println("https://maps.googleapis.com/maps/api/js?key="+MapApiKey+"&libraries=places");
    }

    @GetMapping("/search")
    public String searchByTheme(@RequestParam String theme, Model model) {
        List<Attraction> attraction = courseService.getByTheme(theme);
        model.addAttribute("attraction", attraction);
        return "trip/tokyoSchedule :: #saved-locations";
    }

    @GetMapping("/saveSchedule/{id}")
    public String saveSchedule(Model model, @PathVariable String id) {
        // CourseService를 사용해 Course 객체 저장
        CourseResponseDTO course = courseService.getCourse(id);
        model.addAttribute("course", course);
//        Course savedCourse = courseService.saveCourse(courseRequestDTO, memberId);

        // 모델에 저장된 코스 데이터를 추가
//        model.addAttribute("course", new CourseResponseDTO(savedCourse));

        // SavedTripSchedule.html로 이동
        return "trip/SavedTripSchedule";
    }

}
