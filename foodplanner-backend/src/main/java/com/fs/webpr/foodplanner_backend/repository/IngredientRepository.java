package com.fs.webpr.foodplanner_backend.repository;

import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {

    @Query("SELECT i FROM Ingredient i WHERE i.id NOT IN (SELECT p.ingredient.id FROM Pantry p WHERE p.userId = :userId)")
    List<Ingredient> getIngredientsNotInPantry(UUID userId);

    @Query("SELECT i FROM Ingredient i WHERE i.id NOT IN (SELECT s.ingredient.id FROM ShoppingList s WHERE s.userId = :userId)")
    List<Ingredient> getIngredientsNotOnShoppingList(UUID userId);
}
