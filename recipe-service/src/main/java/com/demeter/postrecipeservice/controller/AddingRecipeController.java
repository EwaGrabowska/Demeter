package com.demeter.postrecipeservice.controller;

import com.demeter.postrecipeservice.dto.RecipeRequest;
import com.demeter.postrecipeservice.dto.RecipeResponse;
import com.demeter.postrecipeservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class AddingRecipeController {

    private final RecipeService recipeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody RecipeRequest recipeRequest) {
        recipeService.createRecipe(recipeRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeResponse> getAllProducts() {
        return recipeService.getAllRecipes();
    }

}
