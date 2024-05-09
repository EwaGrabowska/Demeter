package com.demeter.userservice.service;

import com.demeter.userservice.dto.UserInfoDTO;
import com.demeter.userservice.dto.UserPhotoURL;
import com.demeter.userservice.dto.UserResponse;
import com.demeter.userservice.model.User;
import com.demeter.userservice.repository.UsersRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value(value = "${auth0.userinfo}")
    private String userInfo;
    private final UsersRepository usersRepository;
    private final AWSService awsService;
    @Transactional(readOnly = true)
    @SneakyThrows
    public UserResponse findUserBySub(String sub) {
        User user = getUserBySub(sub);
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
        User currentUser = getUserBySub(sub);
        currentUser.getRecipeHistory().add(recipeId);
        User savedUser = usersRepository.save(currentUser);
        return UserFactory.userToDTO(savedUser);
    }

    private User getUserBySub(String sub){
        User currentUser = usersRepository.findBySub(sub).orElseThrow(()-> new IllegalArgumentException("Cannot find user with sub "+sub));
        return currentUser;
    }

    public UserResponse subcribeUser(String subscribedsub, String currentsub) {
        User currentUser = getUserBySub(currentsub);
        User subscibedUser = getUserBySub(subscribedsub);

        currentUser.getSubscribedAuthors().add(subscibedUser.getSub());
        subscibedUser.getSubscribers().add(currentUser.getSub());

        usersRepository.save(subscibedUser);
        User savedCurrentUser = usersRepository.save(currentUser);
        return UserFactory.userToDTO(savedCurrentUser);
    }

    public UserResponse unsubcribeUser(String subscribedsub, String currentsub) {
        User currentUser = getUserBySub(currentsub);
        User subscibedUser = getUserBySub(subscribedsub);

        currentUser.getSubscribedAuthors().remove(subscibedUser.getSub());
        subscibedUser.getSubscribers().remove(currentUser.getSub());
        usersRepository.save(subscibedUser);
        User savedCurrentUser = usersRepository.save(currentUser);
        return UserFactory.userToDTO(savedCurrentUser);
    }

    public UserResponse getLoggedUser(String token) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(userInfo))
                .setHeader("Authorization", token)
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            UserInfoDTO userInfoDTO = objectMapper.readValue(body, UserInfoDTO.class);

            String sub = userInfoDTO.getSub();
            return findUserBySub(sub);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public UserResponse updateUserWitchPhoto(String fullName, String currentUserSub, String photourl) {
        User userBySub = this.getUserBySub(currentUserSub);
        userBySub.setFullName(fullName);
        if (photourl.equals("")){
            userBySub.setPicture(null);
        }else {
            userBySub.setPicture(photourl);
        }
        User savedUser = usersRepository.save(userBySub);
        return UserFactory.userToDTO(savedUser);
    }

    public UserPhotoURL updateUserPhoto(MultipartFile file) {
        String url = awsService.uploadFile(file);
        return new UserPhotoURL(url);
    }
}
