package com.demeter.postrecipeservice.controller;

import com.demeter.postrecipeservice.dto.IngredientSubstituteResponse;
import com.demeter.postrecipeservice.dto.RecipeResponse;
import com.demeter.postrecipeservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subsitutes")
@RequiredArgsConstructor
public class SubstitutesController {
    private final RecipeService recipeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientSubstituteResponse> getAllSubstitutes(@RequestParam String id) {
        return recipeService.getAllSubstitutes(id);
    }
}
