package com.demeter.recipeservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private long id;
    private String text;
    private String author;
}
