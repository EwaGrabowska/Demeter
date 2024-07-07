package com.demeter.openiaapi.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RecipeRequest {
    @JsonPropertyDescription("Name of the dish if provided, otherwise inferred from context.")
    private String name;
    @JsonPropertyDescription("Number of servings from the recipe if provided. If not provided, inferred from context.")
    private Integer servingSize;
    @JsonPropertyDescription("Author of the dish if provided.")
    private String author;
    @JsonPropertyDescription("Ingredients of the dish.")
    private List<Ingredient> ingredientList;
    @JsonPropertyDescription("Steps to prepare the dish.")
    private List<Step> method;
    @JsonPropertyDescription("Estimated time needed to prepare the ingredients, inferred from context, given in minutes.")
    private Integer preparationTime;
    @JsonPropertyDescription("Estimated time needed to cook the dish, given in minutes.")
    private Integer cookingTime;
    @JsonPropertyDescription("Estimated time needed for dough rising, marinating, etc., given in minutes.")
    private Integer restingTime;
    @JsonPropertyDescription("Total time needed to prepare the dish, given in minutes.")
    private Integer readyToServe;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Step {
        @JsonPropertyDescription("Description of the step.")
        private String text;
        @JsonPropertyDescription("Step number.")
        private int number;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Ingredient {
        @JsonPropertyDescription("Name of the ingredient.")
        private String name;
        @JsonPropertyDescription("Quantity given in numbers.")
        private Double quantity;
        @JsonPropertyDescription("Type of measure. Choose one of the following if possible: łyżka, łyżki, łyżeczka, łyżeczki, g, kg, l, ml, sztuka, sztuki, szczypta, szczypty.")
        private String measuringUnits;
    }
}
