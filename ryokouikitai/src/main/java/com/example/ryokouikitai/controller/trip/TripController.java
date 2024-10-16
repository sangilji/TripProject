package com.example.ryokouikitai.controller.trip;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/trip")
@Component
public class TripController {


    @GetMapping("/schedule")
    public String trip() {
        return "trip/tokyoSchedule";
    }
    @Value("${ApiKey.MapApiKey}")
    private static String MapApiKey;

    public static void API() {
        System.out.println("https://maps.googleapis.com/maps/api/js?key="+MapApiKey+"&libraries=places");
    }


}
