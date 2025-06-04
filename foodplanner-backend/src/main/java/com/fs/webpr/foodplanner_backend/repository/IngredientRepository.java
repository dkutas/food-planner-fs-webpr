package com.fs.webpr.foodplanner_backend.repository;

import com.fs.webpr.foodplanner_backend.entity.dto.response.MissingIngredientByMealResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {

    @Query("SELECT i FROM Ingredient i WHERE i.id NOT IN (SELECT p.ingredient.id FROM Pantry p WHERE p.userId = :userId)")
    List<Ingredient> getIngredientsNotInPantry(UUID userId);

    @Query("SELECT i FROM Ingredient i WHERE i.id NOT IN (SELECT s.ingredient.id FROM ShoppingList s WHERE s.userId = :userId)")
    List<Ingredient> getIngredientsNotOnShoppingList(UUID userId);

    @Query(value = """
             SELECT DISTINCT mp.id, r.id, i.id FROM meal_plan mp\s
             INNER JOIN recipe r on r.id = mp.recipe_id\s
             INNER JOIN recipe_ingredient ri on r.id = ri.recipe_id\s
             INNER JOIN ingredient i on i.id = ri.ingredient_id\s
             WHERE mp.user_id = :userId
             AND mp.start_date >= :startDate
             AND mp.end_date <= :endDate
             AND i.id NOT IN (SELECT p.ingredient_id FROM pantry p WHERE p.user_id = :userId)
            \s""", nativeQuery = true)
    List<MissingIngredientByMealResponseDTO> getAllIngredientMissingByMeal(UUID userId, OffsetDateTime startDate, OffsetDateTime endDate);
}
