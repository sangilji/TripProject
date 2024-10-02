package com.example.ryokouikitai.controller.accompany;

import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.dto.accompany.AccompanyResponseDto;
import com.example.ryokouikitai.service.accompany.AccompanyService;
import com.example.ryokouikitai.service.area.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    private final ThemeService themeService;
    private final AccompanyService accompanyService;

    @GetMapping()
    public String getAccompanyPage(Model model, @PageableDefault(size = 4)Pageable pageable) {
        Page<AccompanyResponseDto> accompanyPage = accompanyService.getAll(pageable);
        model.addAttribute("accompanyList", accompanyPage.getContent());
        model.addAttribute("page", accompanyPage);
        return "accompany/accompanyMain";
    }


    @GetMapping("/write")
    public String getWriteAccompanyPage(Model model) {

        List<Category> theme = themeService.getTheme();
        model.addAttribute("theme", theme);
        return "accompany/write";
    }


    @GetMapping("/detail/{accompany-id}")
    public String getBoard1Detail(@PathVariable("accompany-id") String accompanyId, Model model) {
        Accompany accompany = accompanyService.getById(accompanyId);
        model.addAttribute("accompany", accompany);
        return "accompany/detail";
    }
}
