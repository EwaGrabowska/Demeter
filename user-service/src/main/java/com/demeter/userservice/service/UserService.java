package com.demeter.userservice.service;

import com.demeter.userservice.dto.UserResponse;
import com.demeter.userservice.model.User;
import com.demeter.userservice.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    @Transactional(readOnly = true)
    @SneakyThrows
    public UserResponse findUserBySub(String sub) {
        User user = getCurrentUser(sub);
        return UserFactory.userToDTO(user);
    }

    public UserResponse addRecipeToLikedRecipe(String userId, String recipeId) {
        Long id = Long.parseLong(recipeId);
        User currentUser = findUserById(userId);
        currentUser.likeRecipe(id);
        currentUser = usersRepository.save(currentUser);
        return UserFactory.userToDTO(currentUser);
    }
    private User findUserById(String userId) {
        Long id = Long.parseLong(userId);
        User user = usersRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Cannot find user with id  "+id));
        return user;
    }

    public UserResponse addRecipeToDislikedRecipe(String userId, String recipeId) {
        Long id = Long.parseLong(recipeId);
        User currentUser = findUserById(userId);
        System.out.println(String.valueOf(currentUser.getDisLikedRecipe().size()));
        currentUser.disLikeRecipe(id);
        currentUser = usersRepository.save(currentUser);
        System.out.println(String.valueOf(currentUser.getDisLikedRecipe().size()));
        return UserFactory.userToDTO(currentUser);
    }

    public UserResponse addRecipeToUserHistory(Long recipeId, String sub) {
        User currentUser = getCurrentUser(sub);
        currentUser.getRecipeHistory().add(recipeId);
        User savedUser = usersRepository.save(currentUser);
        return UserFactory.userToDTO(savedUser);
    }

    private User getCurrentUser(String sub){
        User currentUser = usersRepository.findBySub(sub).orElseThrow(()-> new IllegalArgumentException("Cannot find user with sub "+sub));
        return currentUser;
    }
}
