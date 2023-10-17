package com.demeter.recipeservice.client;

import com.demeter.recipeservice.dto.UserResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
@Headers({"Content-Type: application/json"})
public interface UserClient {
    @GetMapping("user")
    @ResponseStatus(HttpStatus.OK)
    UserResponse getLoggedUser(@RequestHeader("Authorization") String token);

    @PutMapping("user/like")
    @ResponseStatus(HttpStatus.OK)
    UserResponse addRecipeToLikedRecipe(@RequestParam("userId") String userId, @RequestParam("recipeId") String recipeId);

    @PutMapping("user/dislike")
    @ResponseStatus(HttpStatus.OK)
    UserResponse addRecipeToDislikedRecipe(@RequestParam("userId") String userId, @RequestParam("recipeId") String recipeId);
}
