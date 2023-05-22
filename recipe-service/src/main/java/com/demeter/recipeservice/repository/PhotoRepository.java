package com.demeter.recipeservice.repository;

import com.demeter.recipeservice.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
