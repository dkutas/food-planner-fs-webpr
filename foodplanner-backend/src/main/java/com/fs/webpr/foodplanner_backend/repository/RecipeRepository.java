package com.fs.webpr.foodplanner_backend.repository;

import com.fs.webpr.foodplanner_backend.entity.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {

    List<Recipe> findAllByUserId(UUID userId);

    @Query("SELECT r FROM Recipe r WHERE r.isPublic = true")
    List<Recipe> findAllPublic();

}
