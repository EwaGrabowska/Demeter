package com.demeter.recipeservice.controller;

import com.demeter.recipeservice.dto.RecipeRequest;
import com.demeter.recipeservice.dto.RecipeResponse;
import com.demeter.recipeservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/addphoto")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadPhoto(@RequestParam("file") MultipartFile file) {
        recipeService.uploadPhoto(file);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeResponse> getAllProducts() {
        return recipeService.getAllRecipes();
    }

}
