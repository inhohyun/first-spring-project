package com.example.firstspringproject.dto;

import com.example.firstspringproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    //댓글 아이디와 해당 댓글의 게시글 아이디
    private Long id;
    //json에서는 article_id라는 이름으로 전송될 것임을 암시
    @JsonProperty("article_id")
    private Long articleId;

    private String nickname;
    private String body;


    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
