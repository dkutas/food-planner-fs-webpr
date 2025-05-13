package com.fs.webpr.foodplanner_backend.entity.dto.response;

import java.util.UUID;

public record MissingIngredientByMealResponseDTO(
        UUID mealPlanId,
        UUID recipeId,
        UUID ingredientId
) {
}
