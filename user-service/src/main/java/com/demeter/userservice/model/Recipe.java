package com.demeter.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Recipe {

    private Long id;
    private String name;
    private Integer servingSize;
    private String author;
    private BigDecimal price;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredientList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Step> method;
    @OneToOne
    private Photo photo;
    private Integer preparationTime;
    private Integer cookingTime;
    private Integer restingTime;
    @Transient
    private Integer readyToServe;


}
