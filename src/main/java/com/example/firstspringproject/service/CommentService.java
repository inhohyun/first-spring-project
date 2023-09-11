package com.example.firstspringproject.service;


import com.example.firstspringproject.dto.CommentDto;
import com.example.firstspringproject.entity.Article;
import com.example.firstspringproject.entity.Comment;
import com.example.firstspringproject.repository.ArticleRepository;
import com.example.firstspringproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    //Service가 Reposiory와 협업할 수 있도록 레파지토리를 땡겨옴
    @Autowired
    private CommentRepository commentRepository;
    //댓글 서비스는 댓글 레파지토리 뿐만 아니라 게시글 레파지토리도 사용해야하니 둘 다 땡겨옴
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
        //조회 : 댓글 목록

//        List<Comment >comments = commentRepository.findByArticleId(articleId);

        //변환 : 엔티티 -> dto
//        List<CommentDto> dtos= new ArrayList<CommentDto>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
        //

        //반환 ->반복문을 스트림으로 처리하기
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }
    //데이터베이스와 관련된 작업이므로 에러 발생시 이전으로 돌아가도록 어노테이션 추가
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article); // 게시글 정보와 게시글 아이디를 가지고 댓글을 생성하여 Comment에 저장
        // 댓글 엔티티를 db로 저저아
        Comment created = commentRepository.save(comment);
        //dto로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 댓글 수정
        target.patch(dto);

        // DB로 갱신
        Comment updated = commentRepository.save(target);
        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);



    }
    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회(및 예외 발생)
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다"));
        //댓글을 db에서 삭제
        commentRepository.delete(target);


        //삭제 댓글을 dto로 반환
        return CommentDto.createCommentDto(target);


    }
}
