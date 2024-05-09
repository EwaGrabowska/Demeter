package com.demeter.userservice.controller;

import com.demeter.userservice.dto.UserPhotoURL;
import com.demeter.userservice.dto.UserResponse;
import com.demeter.userservice.dto.UserUpdateRequest;
import com.demeter.userservice.service.UserRegisterService;
import com.demeter.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUserWithPicture(@RequestBody UserUpdateRequest request) {

        return userService.updateUserWitchPhoto(request.getFullname(), request.getCurrentusersub(), request.getPhotourl());
    }

    @PostMapping("/updatephoto")
    @ResponseStatus(HttpStatus.OK)
    public UserPhotoURL updateUserPhoto(@RequestParam("file") MultipartFile file) {
        return userService.updateUserPhoto(file);
    }



}
