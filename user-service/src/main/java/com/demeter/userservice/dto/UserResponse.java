package com.demeter.userservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String picture;
    private String emailAddress;
    private String sub;
    private Set<Long> subscribedChannelIds = ConcurrentHashMap.newKeySet();
    private Set<Long> videoHistory;
    private Set<Long> likedRecipe = ConcurrentHashMap.newKeySet();
    private Set<Long> disLikedRecipe = ConcurrentHashMap.newKeySet();
}
