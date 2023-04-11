package com.demeter.ingredientsservice;

import com.demeter.ingredientsservice.model.Ingredient;
import com.demeter.ingredientsservice.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;

@SpringBootApplication
public class IngredientServiceApplication {

	@Autowired
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(IngredientServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadTestData(IngredientRepository ingredientRepository){
		mongoTemplate.getDb().drop();
		return args -> {
			Ingredient testIngredient = Ingredient.builder()
					.name("oat")
					.description("Oats are a type of cereal grain commonly grown for human consumption. Typically cooked in water or milk and served hot for breakfast.")
					.substitute(Arrays.asList("quinoa flakes", "rice flakes", "barley flakes"))
					.taste(Arrays.asList("neutral"))
					.build();

			ingredientRepository.save(testIngredient);
		};
	}
}
