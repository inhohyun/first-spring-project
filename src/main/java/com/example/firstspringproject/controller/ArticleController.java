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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
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

        return "redirect:/articles/"+saved.getId();
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

    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 Article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();
        //2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        //3. 뷰 페이지를 설정
        return "articles/index"; // articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터를 등록
        model.addAttribute("article", articleEntity);
        return "articles/edit"; //뷰 페이지 설정
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        //1. dto를 엔티티로 변환한다.
        Article articleEntity = form.toEntitiy();
        log.info(articleEntity.toString());

        //2. 엔티티를 db로 저장한다.
        //2-1: DB에 기존 데이터를 가져온다!
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null); // Optional로 적어도됨


        //2-2 : 기존 데이터가 있다면 값을 갱신한다.
        if (target != null){
            articleRepository.save(articleEntity); // 엔티티가 db로 갱신
        }

        //3. 수정 결과 페이지로 리다이렉트 한다!


        return "redirect:/articles/" + articleEntity.getId();
    }

    //html에서 DeleteMapping을 지원하지 않으므로 GetMapping으로 대체
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!");
        //1. 삭제 대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        //2. 대상을 삭제한다!
        if (target != null){
            // repository가 db에서 대상을 삭제해줌
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }

        //3. 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles";
    }
}
