package com.demeter.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Ingredient {

    private Long id;
    private String name;
    private Double quantity;
    private String measuringUnites;
}
