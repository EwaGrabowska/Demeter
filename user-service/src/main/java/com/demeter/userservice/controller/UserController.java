package com.demeter.userservice.controller;

import com.demeter.userservice.dto.UserResponse;
import com.demeter.userservice.service.UserRegisterService;
import com.demeter.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRegisterService userRegisterService;
    private final UserService userService;

    @GetMapping("/register")
    public String register(@RequestHeader("Authorization") String token){
        userRegisterService.registerUser(token.substring(7));
        return "Registration successfull.";
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    UserResponse findUserbySub(@RequestParam("sub") String sub){
        return userService.findUserBySub(sub);
    }
    @PutMapping("/like")
    @ResponseStatus(HttpStatus.OK)
    UserResponse addRecipeToLikedRecipe(@RequestParam("userId") String userId, @RequestParam("recipeId") String recipeId){
        return userService.addRecipeToLikedRecipe(userId, recipeId);
    }

    @PutMapping("/dislike")
    @ResponseStatus(HttpStatus.OK)
    UserResponse addRecipeToDislikedRecipe(@RequestParam("userId") String userId, @RequestParam("recipeId") String recipeId) {
        return userService.addRecipeToDislikedRecipe(userId, recipeId);
    }

    @PutMapping("/addToHistory")
    @ResponseStatus(HttpStatus.OK)
    UserResponse addRecipeToUserHistory(@RequestParam("recipeId") String recipeId, @RequestHeader("sub") String sub) {
        Long id = Long.parseLong(recipeId);
        return userService.addRecipeToUserHistory(id, sub);
    }

    @PostMapping("/subscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse subscribeAuthor(@PathVariable("userId") String userId, @RequestHeader("sub") String sub){
        return userService.subcribeUser(userId, sub);
    }

    @PostMapping("/unsubscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse unsubscribeAuthor(@PathVariable("userId") String userId, @RequestHeader("sub") String sub){
        return userService.unsubcribeUser(userId, sub);
    }
}
