package com.demeter.recipeservice.dto;

import com.demeter.recipeservice.model.MeasuringUnites;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequest {
    private String name;
    private Double quantity;
    private MeasuringUnites measuringUnites;
}
