package com.example.firstspringproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //일반 Controller는 뷰 템플릿 컨트롤러를 반환하지만, RestController는 기본적으로 Json을 반환함
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello(){
        return "hello world";

    }
}
