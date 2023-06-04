package com.demeter.recipeservice.service;

import brave.Span;
import brave.Tracer;
import com.demeter.recipeservice.client.IngredientClient;
import com.demeter.recipeservice.dto.IngredientSubstituteResponse;
import com.demeter.recipeservice.dto.PhotoResponse;
import com.demeter.recipeservice.dto.RecipeRequest;
import com.demeter.recipeservice.dto.RecipeResponse;
import com.demeter.recipeservice.event.RecipeAddedEvent;
import com.demeter.recipeservice.model.Photo;
import com.demeter.recipeservice.model.Recipe;
import com.demeter.recipeservice.repository.PhotoRepository;
import com.demeter.recipeservice.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RecipeService {

    private final IngredientClient ingredientClient;
    private final RecipeRepository recipeRepository;
    private final Tracer tracer;
    private final KafkaTemplate<String, RecipeAddedEvent> kafkaTemplate;
    private final AWSService AWSService;
    private final PhotoRepository photoRepository;


    public RecipeResponse createRecipe(final RecipeRequest source) {
        Recipe recipe = RecipeFactory.dtoToEntity(source);

        recipe = recipeRepository.save(recipe);
        log.info("Recipe {} is saved", recipe.getId());
        kafkaTemplate.send("dbEventTopic", new RecipeAddedEvent(String.valueOf(recipe.getId())));
        log.info("Notification about saved recipe - id {} sended", recipe.getId());
        return RecipeFactory.entityToDto(recipe);
    }

    public List<RecipeResponse> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(recipe-> RecipeFactory.entityToDto(recipe)).toList();
    }

    private List<IngredientSubstituteResponse> getAllSubstitutesByIngredientsName(List<String> ingredientsName){
        List<IngredientSubstituteResponse> substituteResponses = ingredientClient.hasSubstitute(ingredientsName).stream()
                .filter(substitus -> !substitus.getSubstitute().isEmpty())
                .toList();
        return substituteResponses;
    }

    public List<IngredientSubstituteResponse> getAllSubstitutes(String id) {
        Span ingredientServiceLookup = tracer.nextSpan().name("IngredientServiceLookup");

        try (Tracer.SpanInScope isLookup = tracer.withSpanInScope(ingredientServiceLookup.start())){
            List<String> ingredientsName = recipeRepository.findById(Long.parseLong(id))
                    .map(recipe -> recipe.getIngredientList().stream()
                            .map(ingredient -> ingredient.getName())
                            .toList())
                    .orElseThrow(() -> new IllegalArgumentException("Recipe doesnt exist."));
            return getAllSubstitutesByIngredientsName(ingredientsName);
        }finally {
            ingredientServiceLookup.flush();
        }

    }

    public PhotoResponse uploadPhoto(MultipartFile file) {
        String photoUrl = AWSService.uploadFile(file);
        var photo = new Photo();
        photo.setPhotoUrl(photoUrl);
        System.out.println("photo added");
        Photo savedPhoto = photoRepository.save(photo);
        return new PhotoResponse(String.valueOf(savedPhoto.getId()), savedPhoto.getPhotoUrl());

    }

    public RecipeResponse editRecipe(RecipeResponse recipeResponse) {
        recipeRepository.findById(recipeResponse.getId())
                .orElseThrow(()->new IllegalArgumentException("Recipe doesnt exist. Recipe id: " + recipeResponse.getId()));
        var editedRecipe = RecipeFactory.editRecipe(recipeResponse);
        var newVersionRecipe = recipeRepository.save(editedRecipe);
        return RecipeFactory.entityToDto(newVersionRecipe);
    }
}
