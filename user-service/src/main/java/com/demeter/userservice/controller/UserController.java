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
    public UserResponse register(@RequestHeader("Authorization") String token){
        return userRegisterService.registerUser(token.substring(7));

    }
    @GetMapping("/findbysub")
    public UserResponse findUserBySub(@RequestParam("usersub") String usersub){
        return userService.findUserBySub(usersub);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    UserResponse getLoggedUser(@RequestHeader("Authorization") String token){
        return userService.getLoggedUser(token);
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

    @PostMapping("/subscribe")
    @ResponseStatus(HttpStatus.OK)
    UserResponse subscribeAuthor(@RequestParam("subscribedsub") String subscribedsub, @RequestParam("currentsub") String currentsub){
        return userService.subcribeUser(subscribedsub, currentsub);
    }

    @PostMapping("/unsubscribe")
    @ResponseStatus(HttpStatus.OK)
    UserResponse unsubscribeAuthor(@RequestParam("subscribedsub") String subscribedsub, @RequestParam("currentsub") String currentsub){
        return userService.unsubcribeUser(subscribedsub, currentsub);
    }
}
