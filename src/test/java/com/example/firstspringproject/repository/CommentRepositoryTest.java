package com.example.firstspringproject.repository;

import com.example.firstspringproject.entity.Article;
import com.example.firstspringproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;


    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /*Case 1 : 4번 게시글의 모든 댓글 조회*/
        // 입력 데이터 준비
        Long articleId = 4L;

        // 실제 수행
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 예상하기
        Article article = new Article(4L, "당신의 인생 영화는?", "댓글");
        Comment a = new Comment(1L, article, "Park", "굳 윌 헌팅");
        Comment b = new Comment(2L, article, "Kim", "아이 엠 셈");
        Comment c = new Comment(3L, article, "Choi", "쇼생크의 탈출");
        List<Comment> expected = Arrays.asList(a,b,c);


        // 검증
        assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
    }

    //생략 -> 위랑 같은 방식
    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
//        /*Case 1: "Park의 모든 댓글 조회"*/
//
//        //입력 데이터를 준비
//        String nickname = "Park"
//        // 실제 수행
//        List<Comment> comments = commentRepository.findByNickname(nickname);
//        //예상하기
//
//        //검증
    }
}