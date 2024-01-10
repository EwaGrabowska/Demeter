package com.demeter.recipeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "recipes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer servingSize;
    private String author;
    private String authorSub;
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
    private Integer likes;
    private Integer disLikes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;
    private boolean sketch;


    public Integer incrementLikes(){
        likes = likes + 1;
        return likes;
    }

    public Integer decrementLikes(){
        likes = likes - 1;
        return likes;
    }

    public Integer incrementDislakes(){
        disLikes = disLikes + 1;
        return disLikes;
    }

    public Integer decrementDislakes(){
        disLikes = disLikes - 1;
        return disLikes;
    }

}
