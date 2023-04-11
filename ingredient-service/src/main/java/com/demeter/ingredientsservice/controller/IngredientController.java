package com.demeter.ingredientsservice.controller;

import com.demeter.ingredientsservice.dto.IngredientRequest;
import com.demeter.ingredientsservice.dto.IngredientResponse;
import com.demeter.ingredientsservice.dto.IngredientSubstituteResponse;
import com.demeter.ingredientsservice.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody IngredientRequest ingredientRequest) {
        ingredientService.createIngredient(ingredientRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientResponse> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/substitutes")
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientSubstituteResponse> hasSubstitute(@RequestParam List<String> name){
        return ingredientService.getAllSubstitutes(name);
    }

}
