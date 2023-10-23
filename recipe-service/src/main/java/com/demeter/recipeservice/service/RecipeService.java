package com.demeter.recipeservice.service;

import brave.Span;
import brave.Tracer;
import com.demeter.recipeservice.client.IngredientClient;
import com.demeter.recipeservice.client.UserClient;
import com.demeter.recipeservice.dto.*;
import com.demeter.recipeservice.event.RecipeAddedEvent;
import com.demeter.recipeservice.model.Comment;
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
    private final UserClient userClient;
    private final RecipeRepository recipeRepository;
    private final Tracer tracer;
    private final KafkaTemplate<String, RecipeAddedEvent> kafkaTemplate;
    private final AWSService AWSService;
    private final PhotoRepository photoRepository;
    private final UserService userService;


    public RecipeResponse createRecipe(final RecipeRequest source) {
        Recipe recipe = RecipeFactory.dtoToEntity(source);

        recipe = recipeRepository.save(recipe);
        log.info("Recipe {} is saved", recipe.getId());
        kafkaTemplate.send("dbEventTopic", new RecipeAddedEvent(String.valueOf(recipe.getId())));
        log.info("Notification about saved recipe - id {} sended", recipe.getId());
        return RecipeFactory.dtoToEntity(recipe);
    }

    public List<RecipeResponse> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(recipe-> RecipeFactory.dtoToEntity(recipe)).toList();
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
        return new PhotoResponse(savedPhoto.getId(), savedPhoto.getPhotoUrl());

    }

    public RecipeResponse editRecipe(RecipeResponse recipeResponse) {
        findRecipeById(recipeResponse.getId());
        var editedRecipe = RecipeFactory.editRecipe(recipeResponse);
        var newVersionRecipe = recipeRepository.save(editedRecipe);
        return RecipeFactory.dtoToEntity(newVersionRecipe);
    }

    private Recipe findRecipeById(Long id){
        return recipeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Recipe doesnt exist. Recipe id: " + id));
    }

    public RecipeResponse likeRecipe(String recipeId, String token) {
        Long id = Long.parseLong(recipeId);
        Recipe recipe = findRecipeById(Long.parseLong(recipeId));
        UserResponse currentUser = userService.getLoggedUser(token);
        UserResponse userResponse = userService.addRecipeToLikedHistory(currentUser, id);
        if (currentUser.getLikedRecipe().size()<userResponse.getLikedRecipe().size()){
            return incrementLikes(currentUser, recipe);
        }
        return RecipeFactory.dtoToEntity(recipe);
    }

    public RecipeResponse disLikeRecipe(String recipeId, String token) {
        Long id = Long.parseLong(recipeId);
        Recipe recipe = findRecipeById(id);
        UserResponse currentUser = userService.getLoggedUser(token);
        UserResponse userResponse = userService.addRecipeToDislikedHistory(currentUser, id);
        if (currentUser.getDisLikedRecipe().size()<userResponse.getDisLikedRecipe().size()){
            return incrementDislikes(currentUser, recipe);
        }
        return RecipeFactory.dtoToEntity(recipe);
    }

    private RecipeResponse incrementDislikes(UserResponse currentUser, Recipe recipe) {
        if (currentUser.getLikedRecipe().stream().anyMatch(recipeId -> recipeId == recipe.getId())){
            recipe.decrementLikes();
        }
        recipe.incrementDislakes();
        Recipe savedRecipe = recipeRepository.save(recipe);
        return RecipeFactory.dtoToEntity(savedRecipe);
    }

    private RecipeResponse incrementLikes(UserResponse currentUser, Recipe recipe) {
        if (currentUser.getDisLikedRecipe().stream().anyMatch(recipeId -> recipeId == recipe.getId())){
            recipe.decrementDislakes();
        }
        recipe.incrementLikes();
        Recipe savedRecipe = recipeRepository.save(recipe);
        return RecipeFactory.dtoToEntity(savedRecipe);
    }

    public void addComment(String recipeId, CommentRequest commentDTO) {
        Long id = Long.parseLong(recipeId);
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Recipe doesn't Exist. Recipe id: "+recipeId));
        Comment comment = RecipeFactory.dtoToEntity(commentDTO);
        recipe.getComments().add(comment);
        recipeRepository.save(recipe);
    }

    public List<CommentResponse> getAllComments(String recipeId) {
        Long id = Long.parseLong(recipeId);
        List<Comment> commentList = recipeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Recipe doesn't Exist. Recipe id: "+recipeId))
                .getComments();
        return commentList.stream()
                .map(RecipeFactory::entityToDTO)
                .toList();

    }
}
