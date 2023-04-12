package com.demeter.ingredientsservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(value = "ingredient")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Ingredient {

    @Id
    private String id;
    private String name;
    private List<String> taste;
    private List<String> substitute;
    private String description;
}
