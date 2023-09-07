package com.example.firstspringproject.dto;

import com.example.firstspringproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

//dto : 데이터를 받는 객체
//해당 클래스는 form 데이터를 받아올 그릇이 될 것임

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;


    //데이터가 잘 받아졌는지 확인하기 위해 toString메소드까지 가져오기


    //Entity인 Article 객체 생성하기
    public Article toEntitiy() {

        return new Article(id, title, content);
    }
}
