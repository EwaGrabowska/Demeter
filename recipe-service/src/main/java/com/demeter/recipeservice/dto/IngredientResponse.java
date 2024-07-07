package com.demeter.recipeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientResponse {
    private Long id;
    private String name;
    private Double quantity;
    private String measuringUnits;
}
