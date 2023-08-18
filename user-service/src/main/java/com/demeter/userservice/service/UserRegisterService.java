package com.demeter.userservice.service;

import com.demeter.userservice.dto.UserInfoDTO;
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

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    @Value(value = "${auth0.userinfo}")
    private String userInfo;
    private final UsersRepository userRepository;
    public void registerUser(String tokenValue){
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

            User user = User.builder()
                    .firstName(userInfoDTO.getGivenName())
                    .lastName(userInfoDTO.getFamilyName())
                    .fullName(userInfoDTO.getName())
                    .emailAddress(userInfoDTO.getEmail())
                    .sub(userInfoDTO.getSub())
                    .build();
            userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
