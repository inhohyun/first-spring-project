package com.example.firstspringproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자를 추가
@ToString
// DB가 해당 객체를 인식 가능!
@Entity
@Getter
public class Article {

    @Id // 대표값을 지정!
    @GeneratedValue // 1,2,3,... 자동 생성 어노테이션!
    private Long id;


    @Column
    private String title;
    @Column
    private String content;

    //디폴트 생성자 필요 -> 생성자인데 파라미터가 없는 생성자 -> 상단에서 lombok을 활용해서 세팅



}
