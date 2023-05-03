package com.demeter.kafkaservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeAddedEvent {
    private Long recipeNumber;
}
