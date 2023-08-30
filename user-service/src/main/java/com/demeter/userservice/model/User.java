package com.demeter.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String picture;
    private String emailAddress;
    private String sub;
    @ElementCollection
    private Set<Long> subscribedAuthors = ConcurrentHashMap.newKeySet();
    @ElementCollection
    private Set<Long> subscribers = ConcurrentHashMap.newKeySet();
    @ElementCollection
    private Set<Long> recipeHistory = ConcurrentHashMap.newKeySet();
    @ElementCollection
    private Set<Long> likedRecipe = ConcurrentHashMap.newKeySet();
    @ElementCollection
    private Set<Long> disLikedRecipe = ConcurrentHashMap.newKeySet();

    public void likeRecipe(Long recipeId){
        if (!isLiked(recipeId)){
            likedRecipe.add(recipeId);
        }
        if (isDisliked(recipeId)){
            disLikedRecipe.remove(recipeId);
        }
    }
    public void disLikeRecipe(Long recipeId){
        if (isLiked(recipeId)){
            likedRecipe.remove(recipeId);
        }
        if (!isDisliked(recipeId)){
            disLikedRecipe.add(recipeId);
        }
    }

    private boolean isLiked(Long recipeId){
        return likedRecipe.stream().anyMatch(rId-> recipeId==rId);
    }

    private boolean isDisliked(Long recipeId){
        return disLikedRecipe.stream().anyMatch(rId-> recipeId==rId);
    }
}
