package com.fs.webpr.foodplanner_backend.entity.dto.response;

import java.util.Set;
import java.util.UUID;

public record RecipeResponseDTO(
        UUID id,
        String name,
        String description,
        Long preparationTime,
        KitchenResponseDTO kitchen,
        Set<IngredientResponseDTO> ingredients
) {}
