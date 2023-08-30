package com.demeter.recipeservice.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequest {
    private String text;
    private String author;
    private Integer likeCount;
    private Integer disLikeCount;
}
