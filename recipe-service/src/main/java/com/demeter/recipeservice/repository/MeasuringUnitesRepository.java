package com.demeter.recipeservice.repository;

import com.demeter.recipeservice.model.MeasuringUnites;
import org.springframework.data.jpa.repository.JpaRepository;

interface MeasuringUnitesRepository extends JpaRepository<MeasuringUnites, Integer> {
}
