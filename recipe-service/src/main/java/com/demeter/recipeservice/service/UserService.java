package com.demeter.recipeservice.service;

import brave.Span;
import brave.Tracer;
import com.demeter.recipeservice.client.UserClient;
import com.demeter.recipeservice.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserClient userClient;
    private final Tracer tracer;
    public UserResponse getLoggedUser(String token){
        Span userServiceLookup = tracer.nextSpan().name("UserServiceLookup");

        try (Tracer.SpanInScope isLookup = tracer.withSpanInScope(userServiceLookup.start())){
            return userClient.getLoggedUser(token);
        }finally {
            userServiceLookup.flush();
        }
    }

    public UserResponse addRecipeToLikedHistory(UserResponse currentUser, Long id) {
        Span userServiceLookup = tracer.nextSpan().name("UserServiceLookup");

        try (Tracer.SpanInScope isLookup = tracer.withSpanInScope(userServiceLookup.start())){
            return userClient.addRecipeToLikedRecipe(String.valueOf(currentUser.getId()), String.valueOf(id));
        }finally {
            userServiceLookup.flush();
        }
    }

    public UserResponse addRecipeToDislikedHistory(UserResponse currentUser, Long id) {
        Span userServiceLookup = tracer.nextSpan().name("UserServiceLookup");

        try (Tracer.SpanInScope isLookup = tracer.withSpanInScope(userServiceLookup.start())){
            return userClient.addRecipeToDislikedRecipe(String.valueOf(currentUser.getId()), String.valueOf(id));
        }finally {
            userServiceLookup.flush();
        }
    }
}
