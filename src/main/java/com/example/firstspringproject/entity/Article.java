package com.example.firstspringproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자를 추가
@ToString

@Entity// DB가 해당 객체를 인식 가능! -> 해당 클래스로 테이블을 만듦
@Getter
public class Article {

    @Id // 대표값을 지정!
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3,... 자동 생성 어노테이션! -> db가 id를 자동 생성하도록
    private Long id;


    @Column
    private String title;
    @Column
    private String content;

    //수정중 부분 null값을 방지하기 위해 부분적으로 값이 없는채로 업데이트할 경우 없는 부분은 기존 값을 유지
    public void patch(Article article) {
        if (article.title != null){
            this.title = article.title;
        }
        if (article.content != null){
            this.content = article.content;

        }
    }

    //디폴트 생성자 필요 -> 생성자인데 파라미터가 없는 생성자 -> 상단에서 lombok을 활용해서 세팅


}
