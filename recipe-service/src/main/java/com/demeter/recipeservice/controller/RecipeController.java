package com.demeter.recipeservice.controller;

import com.demeter.recipeservice.dto.*;
import com.demeter.recipeservice.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadRecipeResponse createRecipe(@RequestBody RecipeRequest recipeRequest) throws JsonProcessingException {
        RecipeResponse recipeResponse = recipeService.createRecipe(recipeRequest);
        return new UploadRecipeResponse(String.valueOf(recipeResponse.getId()));
    }

    @PostMapping("/addphoto")
    @ResponseStatus(HttpStatus.CREATED)
    public UploadPhotoResponse uploadPhoto(@RequestParam("file") MultipartFile file) {
        PhotoResponse photoResponse = recipeService.uploadPhoto(file);
        return new UploadPhotoResponse(photoResponse.getId(), photoResponse.getPhotoUrl());
    }

    @GetMapping("/allrecipes")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeResponse> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RecipeResponse editRecipe(@RequestBody RecipeResponse recipeResponse){
        return recipeService.editRecipe(recipeResponse);
    }

    @PostMapping("/{recipeId}/like")
    @ResponseStatus(HttpStatus.OK)
    public RecipeResponse likeRecipe(@PathVariable("recipeId") String recipeId, @RequestHeader("Authorization") String token) {
        return recipeService.likeRecipe(recipeId, token);
    }

    @PostMapping("/{recipeId}/dislike")
    @ResponseStatus(HttpStatus.OK)
    public RecipeResponse disLikeRecipe(@PathVariable("recipeId") String recipeId, @RequestHeader("Authorization") String token) {
        return recipeService.disLikeRecipe(recipeId, token);
    }

    @PostMapping("/{recipeId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public RecipeResponse addComment(@PathVariable("recipeId") String recipeId, @RequestBody CommentRequest commentDTO){
        return recipeService.addComment(recipeId, commentDTO);
    }

    @GetMapping("/{recipeId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponse> getComments(@PathVariable("recipeId") String recipeId, @RequestBody CommentRequest commentDTO){
        return recipeService.getAllComments(recipeId);
    }

}

