package com.example.ryokouikitai.controller.trip;

import com.example.ryokouikitai.domain.area.Attraction;
import com.example.ryokouikitai.dto.trip.AttractionDto;
import com.example.ryokouikitai.global.response.BaseResponse;
import com.example.ryokouikitai.service.trip.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trip")
@Slf4j
public class TripRestController {

    private final TripService tripService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String theme) {
//        List<Attraction> attractions = tripService.getAttraction(theme);
        List<Attraction> attractions = tripService.getAttractionByCourseAttraction(theme);
        return BaseResponse.okWithData(HttpStatus.OK, "어트랙션 조회", attractions);
    }

    @PostMapping("/attraction")
    public ResponseEntity<?> attractionSave(AttractionDto attractionDto) {
        Attraction attraction = tripService.addAttraction(attractionDto);
        return BaseResponse.okWithData(HttpStatus.OK, "어트랙션 추가완료",attraction);
    }
}
