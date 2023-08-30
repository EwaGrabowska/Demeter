package com.demeter.recipeservice.dto;

import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private long id;
    private String text;
    private String author;
    private Integer likeCount;
    private Integer disLikeCount;
}
