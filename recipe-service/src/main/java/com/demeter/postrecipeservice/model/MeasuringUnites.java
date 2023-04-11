package com.demeter.postrecipeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "measuring_unites")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MeasuringUnites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
