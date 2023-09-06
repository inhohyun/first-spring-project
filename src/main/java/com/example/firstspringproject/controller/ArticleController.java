package com.example.firstspringproject.controller;

import com.example.firstspringproject.dto.ArticleForm;
import com.example.firstspringproject.entity.Article;
import com.example.firstspringproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@Slf4j // -> 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";

    }

    //form에서 post형식을로 데이터를 보낼 것이므로 PostMappring을 사용
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

//        System.out.println(form.toString()); -> 로깅기능으로 대체!

        log.info(form.toString());


        // DTO(데이터 객체)를 데이터베이스에 저장하기
        //1. DTO -> Entity로 변환!
        Article article = form.toEntitiy();

//        System.out.println(article.toString());
        log.info(article.toString());


        //2. Repository에게 Entity를 DB안에 저장하게 함!
        //CrudRepository에 있는 save메소드를 활용해 데이터를 저장
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());

        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){

        //1. id로 데이터를 가져옴!
//        Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        //3. 보여줄 페이지를 설정

        return "articles/show";
    }
}
