package com.demeter.openiaapi.controller;

import com.demeter.openiaapi.dto.RecipeRequest;
import com.demeter.openiaapi.dto.ValidationResponse;
import com.demeter.openiaapi.service.OpenIAApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/openiaapi")
@RequiredArgsConstructor
public class OpenIAApiController {

    private final OpenIAApiService service;

    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.OK)
    public ValidationResponse validateText(@RequestBody String text) {
        return service.validateText(text);
    }

    @PostMapping("/convert")
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeRequest convertTextToRecipe(@RequestBody String text) {
        return service.createRecipe(text);
    }
}
