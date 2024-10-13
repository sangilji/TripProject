package com.example.ryokouikitai.controller.area;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/area")
@Controller
@Slf4j
public class AreaController {

    @GetMapping("/select")
    public String login(Model model) {
        return "area/selectMain";
    }


    @GetMapping("/{area_name}/main-proc")
    public String login(Model model, @PathVariable("area_name") String area) {
        log.info(area);
        model.addAttribute("areaName",area);
        return "area/mainProc";
    }

    @GetMapping("/{area_name}")
    public String main(Model model, @PathVariable("area_name") String area) {
        model.addAttribute("areaName",area);
        return "area/main";
    }
    @GetMapping("/{area_name}/chat")
    public String chat(Model model, @PathVariable("area_name") String area) {
        model.addAttribute("areaName",area);
        return "area/chat";
    }
}
