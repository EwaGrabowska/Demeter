package com.demeter.postrecipeservice.repository;

import com.demeter.postrecipeservice.model.MeasuringUnites;
import org.springframework.data.jpa.repository.JpaRepository;

interface MeasuringUnitesRepository extends JpaRepository<MeasuringUnites, Integer> {
}
