package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// 회원 가입 후 메인 페이지로 갈 수 있도록 작성
public class MainController {

    @GetMapping(value = "/")
    public String main() {

        return "main";

    }

}