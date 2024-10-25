package com.example.ryokouikitai.controller.member;

import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.domain.area.Course;
import com.example.ryokouikitai.domain.board.Board;
import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.domain.member.MemberInfo;
import com.example.ryokouikitai.dto.member.JoinForm;
import com.example.ryokouikitai.dto.member.LoginForm;
import com.example.ryokouikitai.dto.member.UpdateForm;
import com.example.ryokouikitai.dto.member.MemberResponseDto;
import com.example.ryokouikitai.service.accompany.AccompanyService;
import com.example.ryokouikitai.service.board.BoardService;
import com.example.ryokouikitai.service.course.CourseService;
import com.example.ryokouikitai.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final BoardService boardService;
    //    private final AccompanyService accompanyService;
    private final CourseService courseService;
    private final AccompanyService accompanyService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "members/login";
    }
    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm, BindingResult result, HttpSession session) {
            Member member;
        try {
            member = memberService.login(loginForm);
            MemberInfo memberInfo = member.toMemberInfo();
            session.setAttribute("memberInfo", memberInfo);
        }catch (Exception e) {
            result.addError(new FieldError("loginForm", "password", e.getMessage()));
            return "members/login";
        }

        return "redirect:/area/select";
    }

    @GetMapping("/join")
    public String getJoin(Model model) {
        model.addAttribute("joinForm", new JoinForm());
        return "members/join";
    }

    @PostMapping("/join")
    public String join(@Valid JoinForm joinForm, BindingResult result) {

        if (!joinForm.getPassword().equals(joinForm.getPasswordCheck())) {
            System.out.println("비번같지않음");
            result.addError(new FieldError("joinForm", "passwordCheck", "비밀번호가 같지 않습니다."));
        }

        if(result.hasErrors()){
            System.out.println("에러가 있음");
            return "members/join";
        }
        memberService.join(joinForm);
        return "redirect:/members/login";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "members/mypage";
    }

    @PostMapping("/mypage")
    public String updateInfo(HttpSession session, UpdateForm updateForm) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        memberInfo = memberService.updateInfo(memberInfo.getId(), updateForm);
        session.setAttribute("memberInfo", memberInfo);
        return "members/mypage";
    }
    @GetMapping("/mypage2")
    public String mypage2(@RequestParam(required = false) String kind, Model model, @PageableDefault(size = 7) Pageable pageable, HttpSession session){
        if (kind == null) {
            kind = "Trip";
        }
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        if (kind.equals("Trip")) {
            Page<Course> memberResponseDto = courseService.getByMemberId(memberInfo.getId(),pageable);
            model.addAttribute("post", memberResponseDto.getContent());
            model.addAttribute("page", memberResponseDto);

        }else if (kind.equals("Accompany")){
            Page<Accompany> memberResponseDto = accompanyService.getByMemberId(memberInfo.getId(), pageable);
            model.addAttribute("post", memberResponseDto.getContent());
            model.addAttribute("page", memberResponseDto);
        }else {
            Page<Board> memberResponseDto = boardService.getByMemberId(memberInfo.getId(),pageable);
            model.addAttribute("post", memberResponseDto.getContent());
            model.addAttribute("page", memberResponseDto);
        }
        model.addAttribute("kind", kind);

        return "members/mypage2";
    }
    @GetMapping("/mypage3")
    public String mypage3(){

        return "members/mypage3";
    }
    @GetMapping("/mypage4")
    public String mypage4(){

        return "members/mypage4";
    }
    @GetMapping("/mypage5")
    public String mypage5(Model model, @PageableDefault(size=7) Pageable pageable, HttpSession session){
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        Page<Board> memberLikeDto = boardService.findLike(memberInfo.getId(),pageable);
        model.addAttribute("post", memberLikeDto.getContent());
        model.addAttribute("page", memberLikeDto);

        return "members/mypage5";
    }

    // 필터기능
    @GetMapping("/searchTrip")
    public String searchTrip(Model model, @PageableDefault(size = 7) Pageable pageable, HttpSession session) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        Page<Course> memberResponseDto = courseService.getByMemberId(memberInfo.getId(),pageable);
        model.addAttribute("post", memberResponseDto.getContent());
        model.addAttribute("page", memberResponseDto);
        model.addAttribute("kind", "Trip");
        // 상위 데이터에 데이터 잡아서 지금 받은 걸로 수정
        return "members/mypage2 :: #post-table";
    }

    @GetMapping("/searchAccompany")
    public String searchAccompany(Model model, @PageableDefault(size = 7) Pageable pageable, HttpSession session) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        Page<Accompany> memberResponseDto = accompanyService.getByMemberId(memberInfo.getId(), pageable);
        model.addAttribute("post", memberResponseDto.getContent());
        model.addAttribute("page", memberResponseDto);
        model.addAttribute("kind", "Accompany");
        // 상위 데이터에 데이터 잡아서 지금 받은 걸로 수정
        return "members/mypage2 :: #post-table";
    }

    @GetMapping("/searchBoard")
    public String searchBoard(Model model, @PageableDefault(size = 7) Pageable pageable, HttpSession session) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        Page<Board> memberResponseDto = boardService.getByMemberId(memberInfo.getId(),pageable);
        model.addAttribute("post", memberResponseDto.getContent());
        model.addAttribute("page", memberResponseDto);
        model.addAttribute("kind", "Board");
        // 상위 데이터에 데이터 잡아서 지금 받은 걸로 수정
        return "members/mypage2 :: #post-table";
    }
}
