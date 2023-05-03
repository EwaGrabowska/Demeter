package com.demeter.recipeservice.dto;

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
    private String method;
    private BigDecimal price;
    private List<IngredientRequest> ingredientList;
    private Integer preparationTime;
    private Integer cookingTime;
    private Integer restingTime;
}
