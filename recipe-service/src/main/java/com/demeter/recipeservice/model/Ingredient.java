package com.demeter.recipeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ingredients")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    private MeasuringUnites measuringUnites;
}
