package com.demeter.userservice.service;

import com.demeter.userservice.dto.UserInfoDTO;
import com.demeter.userservice.dto.UserResponse;
import com.demeter.userservice.model.User;
import com.demeter.userservice.repository.UsersRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    @Value(value = "${auth0.userinfo}")
    private String userInfo;
    private final UsersRepository userRepository;
    public UserResponse registerUser(String tokenValue){
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(userInfo))
                .setHeader("Authorization", String.format("Bearer %s", tokenValue))
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

            Optional<User> userBySub = userRepository.findBySub(userInfoDTO.getSub());
            if (userBySub.isPresent()){
                return UserFactory.userToDTO(userBySub.get());
            }else {
                User user = User.builder()
                        .firstName(userInfoDTO.getGivenName())
                        .lastName(userInfoDTO.getFamilyName())
                        .fullName(userInfoDTO.getName())
                        .emailAddress(userInfoDTO.getEmail())
                        .sub(userInfoDTO.getSub())

                        .subscribedAuthors(ConcurrentHashMap.newKeySet())
                        .subscribers(ConcurrentHashMap.newKeySet())
                        .likedRecipe(ConcurrentHashMap.newKeySet())
                        .disLikedRecipe(ConcurrentHashMap.newKeySet())
                        .recipeHistory(ConcurrentHashMap.newKeySet())
                        .build();
                User saved = userRepository.save(user);
                return UserFactory.userToDTO(saved);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
