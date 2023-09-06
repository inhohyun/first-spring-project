package com.example.firstspringproject.repository;

import com.example.firstspringproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;


//CrudRepository를 상속받아 Repository 인터페이스 구성하기
public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Override
    ArrayList<Article> findAll();

}
