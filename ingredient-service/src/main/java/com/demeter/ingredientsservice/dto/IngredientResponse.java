package com.demeter.ingredientsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientResponse {
    private String id;
    private String name;
    private List<String> taste;
    private List<String> substitute;
    private String description;
}
