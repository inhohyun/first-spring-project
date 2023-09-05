package com.example.firstspringproject.dto;

//dto : 데이터를 받는 객체
//해당 클래스는 form 데이터를 받아올 그릇이 될 것임
public class ArticleForm {
    private String title;
    private String content;

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //데이터가 잘 받아졌는지 확인하기 위해 toString메소드까지 가져오기

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
