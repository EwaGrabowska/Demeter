package com.demeter.recipeservice.controller;

import com.demeter.recipeservice.dto.*;
import com.demeter.recipeservice.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping("/paginated")
    public Page<RecipeResponse> getAllRecipesPaginated(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return recipeService.getAllRecipesPageble(page, size);
    }

    @GetMapping("/myrecipes/paginated")
    public Page<RecipeResponse> getUserRecipesPaginated(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("usersub") String usersub
    ) {
        return recipeService.getAllUserRecipesPageble(page, size, usersub);
    }

    @PostMapping("/likedrecipe/paginated")
    public Page<RecipeResponse> getLikedRecipesPaginated(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestBody List<Integer> likedRecipeIdList
    ) {
        return recipeService.getAllLikedRecipesPageble(page, size, likedRecipeIdList);
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
    public void addComment(@PathVariable("recipeId") String recipeId, @RequestBody CommentRequest commentDTO){
        recipeService.addComment(recipeId, commentDTO);
    }

    @GetMapping("/{recipeId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponse> getComments(@PathVariable("recipeId") String recipeId){
        return recipeService.getAllComments(recipeId);
    }

}

