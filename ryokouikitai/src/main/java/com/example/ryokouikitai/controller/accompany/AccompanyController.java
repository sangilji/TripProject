package com.example.ryokouikitai.controller.accompany;

import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.domain.member.MemberInfo;
import com.example.ryokouikitai.dto.accompany.AccompanyDetailDto;
import com.example.ryokouikitai.dto.accompany.AccompanyResponseDto;
import com.example.ryokouikitai.service.accompany.AccompanyService;
import com.example.ryokouikitai.service.area.ThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accompany")
@Slf4j
public class AccompanyController {

    private final ThemeService themeService;
    private final AccompanyService accompanyService;

    @GetMapping()
    public String getAccompanyPage(@RequestParam(required = false) String theme, @RequestParam(required = false) String memberId, @RequestParam(required = false) String title,Model model, @PageableDefault(size = 4) Pageable pageable) {
        if (theme == null) {
            theme = ""; // 원하는 기본값으로 변경
        }
        if (memberId == null) {
            memberId = ""; // 원하는 기본값으로 변경
        }
        if (title == null) {
            title = ""; // 원하는 기본값으로 변경
        }
        log.info("theme-{} memberId-{} title-{}", theme, memberId, title);
        Page<AccompanyResponseDto> accompanyPage = accompanyService.getByThemeOrMemberIdOrTitle(theme, memberId, title, pageable);
        model.addAttribute("accompanyList", accompanyPage.getContent());
        model.addAttribute("page", accompanyPage);
        model.addAttribute("category", theme);
        model.addAttribute("memberId", memberId);
        model.addAttribute("title", title);
        return "accompany/accompanyMain";
    }


    @GetMapping("/write")
    public String getWriteAccompanyPage(Model model) {

        List<Category> theme = themeService.getTheme();
        model.addAttribute("theme", theme);
        return "accompany/write";
    }

    @GetMapping("/rewrite/{accompany-id}")
    public String getReWriteAccompanyPage(@RequestParam String theme, @RequestParam String memberId, @RequestParam String title,HttpSession session, @PathVariable("accompany-id") String accompanyId, Model model) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        Accompany accompany = accompanyService.getById(memberInfo, accompanyId).getAccompany();
        model.addAttribute("accompany", accompany);
        List<Category> theme1 = themeService.getTheme();
        model.addAttribute("theme", theme1);
        return "accompany/rewrite";
    }


    @GetMapping("/detail/{accompany-id}")
    public String getDetail(HttpSession session, @PathVariable("accompany-id") String accompanyId, Model model) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        AccompanyDetailDto accompanyDetailDto = accompanyService.getById(memberInfo, accompanyId);
        model.addAttribute("accompany", accompanyDetailDto);
        return "accompany/detail";
    }

    @GetMapping("/search")
    public String search(@RequestParam String theme, @RequestParam String memberId, @RequestParam String title, Model model, @PageableDefault(size = 4) Pageable pageable) {
        log.info("{}", theme);
        Page<AccompanyResponseDto> accompany = accompanyService.getByThemeOrMemberIdOrTitle(theme, memberId, title, pageable);
        model.addAttribute("accompanyList", accompany.getContent());
        model.addAttribute("page", accompany);
        model.addAttribute("category", theme);
        model.addAttribute("memberId", memberId);
        model.addAttribute("title", title);

        return "accompany/accompanyMain :: #square-group";
    }

    @GetMapping("/searchContent")
    public String searchContent(@RequestParam String title, Model model, @PageableDefault(size = 4) Pageable pageable) {
//        Page<AccompanyResponseDto> accompany = accompanyService.getByTitle(title, pageable);
//        model.addAttribute("accompanyList", accompany.getContent());
//        model.addAttribute("page", accompany);

        return "accompany/accompanyMain :: #square-group";
    }
}
