package com.demeter.userservice.service;

import com.demeter.userservice.dto.UserResponse;
import com.demeter.userservice.model.User;

public class UserFactory {

    public static UserResponse userToDTO(User source){
        return UserResponse.builder()
                .id(source.getId())
                .sub(source.getSub())
                .emailAddress(source.getEmailAddress())
                .picture(source.getPicture())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .fullName(source.getFullName())
                .likedRecipe(source.getLikedRecipe())
                .disLikedRecipe(source.getDisLikedRecipe())
                .subscribedChannelIds(source.getSubscribedChannelIds())
                .build();
    }
}
