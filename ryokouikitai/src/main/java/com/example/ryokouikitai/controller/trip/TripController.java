package com.example.ryokouikitai.controller.trip;


import com.example.ryokouikitai.domain.area.Attraction;
import com.example.ryokouikitai.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


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


}
