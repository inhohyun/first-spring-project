package com.example.firstspringproject.api;

import com.example.firstspringproject.dto.ArticleForm;
import com.example.firstspringproject.entity.Article;
import com.example.firstspringproject.repository.ArticleRepository;
import com.example.firstspringproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //RestApi용 컨트롤러 데이터(JSON)을 반환
public class ArticleApiController {
    @Autowired // DI, 생성 객체를 가져와 연결
    private ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {

        return articleService.index();
    }

    @GetMapping("/api/articles")
    public Article show(@PathVariable Long id) {

        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    //ResponseEntity로 보내면 상태코드를 함께 보내줄 수 있음
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        //컨트롤러는 뭘 받아오고 뭘 리턴해야하는지만 알고있음
        Article updated = articleService.update(id, dto);

        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/articles{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);

        return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //트랜잭션
}
