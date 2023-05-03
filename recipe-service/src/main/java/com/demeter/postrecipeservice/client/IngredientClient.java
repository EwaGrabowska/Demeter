package com.demeter.postrecipeservice.client;

import com.demeter.postrecipeservice.dto.IngredientSubstituteResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "ingredient-service")
@Retry(name = "ingredient")
public interface IngredientClient {
    @GetMapping("ingredients/substitutes")
    @ResponseStatus(HttpStatus.OK)
    List<IngredientSubstituteResponse> hasSubstitute(@RequestParam List<String> name);
}
