package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.validation.BindingResult;
import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
// 회원 가입을 위한 페이지
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    // 회원 가입 페이지로 이동할 수 있도록 MemberController 클래스에 메소드를 작성
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    // 검사 후 결과를 bindingResult에 담아줌
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

        // bindingResult.hasErrors()를 호출하여 에러가 있다면 회원 가입 페이지로 이동
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            // 회원 가입 시 중복 회원 가입 예외가 발생하면 에러 메시지를 뷰로 전달
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";

    }
}