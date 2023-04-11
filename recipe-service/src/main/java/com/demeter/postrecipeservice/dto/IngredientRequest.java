package com.demeter.postrecipeservice.dto;

import com.demeter.postrecipeservice.model.MeasuringUnites;
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
