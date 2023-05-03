package com.demeter.postrecipeservice.controller;

import com.demeter.postrecipeservice.dto.IngredientSubstituteResponse;
import com.demeter.postrecipeservice.service.RecipeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/recipes/subsitutes")
@RequiredArgsConstructor
@Slf4j
public class SubstitutesController {
    private final RecipeService recipeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = "ingredient")
    @TimeLimiter(name="ingredient")
    @Retry(name = "ingredient", fallbackMethod = "fallbackGetAllSubstitutesMethod")
    public CompletableFuture<List<IngredientSubstituteResponse>> getAllSubstitutes(@RequestParam String id) {
        return CompletableFuture.supplyAsync(()->recipeService.getAllSubstitutes(id));
    }

    public CompletableFuture<List<IngredientSubstituteResponse>> fallbackGetAllSubstitutesMethod(Throwable t) {
        log.info("Message from fallback method: "+ t.getMessage());
        return CompletableFuture.supplyAsync(()->new ArrayList<>());
    }
}
