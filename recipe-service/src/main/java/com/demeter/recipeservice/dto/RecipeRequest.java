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
public class RecipeRequest {
    private String name;
    private Integer servingSize;
    private String author;
    private BigDecimal price;
    private List<IngredientRequest> ingredientList;
    private List<StepRequest> method;
    private Integer preparationTime;
    private Integer cookingTime;
    private Integer restingTime;
    private Photo photo;
}
