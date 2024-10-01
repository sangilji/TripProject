package com.example.ryokouikitai.controller.accompany;

import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.service.area.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accompany")
public class AccompanyController {

    private final ThemeService areaService;

    @GetMapping()
    public String getAccompanyPage() {
        return "accompany/accompanyMain";
    }


    @GetMapping("/write")
    public String getWriteAccompanyPage(Model model) {

        List<Category> theme = areaService.getTheme();
        model.addAttribute("theme", theme);
        return "accompany/write";
    }


    @GetMapping("/detail/{accompany-id}")
    public String getBoard1Detail(@PathVariable("accompany-id") String accompanyId) {
        return "accompany/detail";
    }
}
