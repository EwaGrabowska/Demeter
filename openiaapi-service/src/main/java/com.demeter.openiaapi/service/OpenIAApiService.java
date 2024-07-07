package com.demeter.openiaapi.service;

import com.demeter.openiaapi.dto.RecipeRequest;
import com.demeter.openiaapi.dto.ValidationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenIAApiService {

    private final OpenAiService service;
    public ValidationResponse validateText(String text) {
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("user", "Content: " + text));
        messages.add(new ChatMessage("user", "Valide if given content is food recipe."));
        messages.add(new ChatMessage("user", "If the provided text is a culinary recipe, return only number 1, if it is not, return an explanation in polish language in one sentence about what the text is about."));

        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .build();

        ChatCompletionResult response =  service.createChatCompletion(completionRequest);
        String textResponse = response.getChoices().get(0).getMessage().getContent();
        ValidationResponse validationResponse = new ValidationResponse();
        if (textResponse.equals("1")) {
            validationResponse.setValidationResult(true);
        } else {
            validationResponse.setMessage(textResponse);
        }
        return validationResponse;
    }

    public RecipeRequest createRecipe(String text){
        String classCode = ClassCodeGenerator.generateClassCode(RecipeRequest.class);
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("user", "Content: " + text));
        messages.add(new ChatMessage("user", "Translate recipe into Polish."));
        messages.add(new ChatMessage("user", "Provide the given recipe in Polish in JSON format based on the description of the Recipe.class: " + classCode));
        messages.add(new ChatMessage("user", "Provide the answer without a comment, only pure recipe in Polish in JSON"));

        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model("gpt-4o-2024-05-13")
                .messages(messages)
                .build();

        ChatCompletionResult response =  service.createChatCompletion(completionRequest);
        String jsonResponse = response.getChoices().get(0).getMessage().getContent();
        jsonResponse = jsonResponse.replace("```json\n", "");
        jsonResponse = jsonResponse.replace("```", "");
        System.out.println(jsonResponse);
        return parseJsonToRecipe(jsonResponse);
    }

    private RecipeRequest parseJsonToRecipe(String jsonResponse){
        RecipeRequest recipe = null;

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            recipe = objectMapper.readValue(jsonResponse, RecipeRequest.class);
        }catch (JsonProcessingException e){
            System.out.println(e);
            System.out.println("nie udało się sparsować");
        }
        return recipe;
    }
}
