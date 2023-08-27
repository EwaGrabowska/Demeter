package com.demeter.recipeservice.client;

import com.demeter.recipeservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("user")
    @ResponseStatus(HttpStatus.OK)
    UserResponse findUserbySub(@RequestParam("sub") String sub);

    @PutMapping("user/like")
    @ResponseStatus(HttpStatus.OK)
    UserResponse addRecipeToLikedRecipe(@RequestParam("userId") String userId, @RequestParam("recipeId") String recipeId);

    @PutMapping("user/dislike")
    @ResponseStatus(HttpStatus.OK)
    UserResponse addRecipeToDislikedRecipe(@RequestParam("userId") String userId, @RequestParam("recipeId") String recipeId);
}
