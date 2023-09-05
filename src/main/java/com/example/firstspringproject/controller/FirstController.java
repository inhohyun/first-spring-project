package com.example.firstspringproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        // 변수에 값을 저장하기 위해 모델에 저장
        model.addAttribute("username", "ihh");


        return "greetings";
    }
    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname", "홍길동");

        //return은 보여줄 viewTemplate 페이지를 의미함
        return "goodbye";
    }

}
