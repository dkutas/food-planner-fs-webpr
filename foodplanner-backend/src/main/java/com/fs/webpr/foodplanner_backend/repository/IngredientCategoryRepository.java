package com.fs.webpr.foodplanner_backend.repository;

import com.fs.webpr.foodplanner_backend.entity.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, UUID> {
}
