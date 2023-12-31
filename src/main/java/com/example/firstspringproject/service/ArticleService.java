package com.example.firstspringproject.service;

import com.example.firstspringproject.dto.ArticleForm;
import com.example.firstspringproject.entity.Article;
import com.example.firstspringproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 해당 클래스를 서비스로 인식하여 스프링 부트에 객체를 생성(등록)
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();

    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntitiy();
        // 이미 있는 id로 수정하려할때 기존 데이터가 삭제되는것을 방지
        if (article.getId() != null){
            return null;
        }

        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        //1. 수정용 엔티티 생성
        Article article = dto.toEntitiy();

        //2. 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리
        if (target == null || id != article.getId()){
            return null;

        }
        //4. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        //1. 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);

        //2. 잘못된 요청 처리(대상이 없는 경우)
        if (target == null){
            return null;
        }

        articleRepository.delete(target);
        return target;
    }
    @Transactional // 해당 메소드를 트랙잭션으로 묶음 -> 실패하면 초기로 돌아옴
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 entity 묶음으로 반환
        List<Article> articleList = dtos.stream().map(dto -> dto.toEntitiy())
                .collect(Collectors.toList());

        // entity 묶음을 DB로 저장
        articleList.stream().forEach(article -> articleRepository.save(article));


        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결재 실패")
        );

        // 결과값 반환
        return articleList;


    }
}
