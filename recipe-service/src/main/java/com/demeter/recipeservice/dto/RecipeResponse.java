package com.demeter.recipeservice.dto;

import com.demeter.recipeservice.model.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse {
    private Long id;
    private String name;
    private Integer servingSize;
    private String author;
    private String authorSub;
    private BigDecimal price;
    private List<IngredientResponse> ingredientList;
    private List<StepResponse> method;
    private Integer preparationTime;
    private Integer cookingTime;
    private Integer restingTime;
    private Integer readyToServe;
    private Photo photo;
    private Integer likes;
    private Integer disLikes;
    private List<CommentResponse> commentResponseList;
}
