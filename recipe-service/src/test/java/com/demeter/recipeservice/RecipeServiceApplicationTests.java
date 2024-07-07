package com.demeter.recipeservice;


import com.demeter.recipeservice.dto.IngredientRequest;
import com.demeter.recipeservice.dto.RecipeRequest;
import com.demeter.recipeservice.model.MeasuringUnites;
import com.demeter.recipeservice.repository.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class RecipeServiceApplicationTests {
	@Autowired
	private MockMvc mockMvc;
    @Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RecipeRepository recipeRepository;
	@Test
	void contextLoads() {
	}
	@Test
	void test_recipe_added_to_db_correctly() throws Exception {
		RecipeRequest recipeRequest = getTestRecipeRequest();
		String productRequestString = objectMapper.writeValueAsString(recipeRequest);
		System.out.println(productRequestString);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/recipes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, recipeRepository.findAll().size());
	}

	private RecipeRequest getTestRecipeRequest() {
		List<IngredientRequest> ingredientRequestList = new ArrayList<>();
		ingredientRequestList.add(IngredientRequest.builder()
				.name("salt")
				.quantity(0.125)
				.measuringUnits(MeasuringUnites.builder().name("ts").build())
				.build());

		ingredientRequestList.add(IngredientRequest.builder()
				.name("oat")
				.quantity(100.0)
				.measuringUnits(MeasuringUnites.builder().name("g").build())
				.build());

		ingredientRequestList.add(IngredientRequest.builder()
				.name("watcher")
				.quantity(400.0)
				.measuringUnits(MeasuringUnites.builder().name("ml").build())
				.build());

		return RecipeRequest.builder()
				.name("Porridge")
				.author("Ewa Grabowska")
				.method("Pur all ingredients together, bring to the boil and cook 5 minutes.")
				.ingredientList(ingredientRequestList)
				.preparationTime(2)
				.cookingTime(5)
				.restingTime(0)
				.servingSize(2)
				.build();
	}


}
