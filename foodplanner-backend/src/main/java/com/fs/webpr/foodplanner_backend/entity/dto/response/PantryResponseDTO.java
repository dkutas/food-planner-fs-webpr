package com.fs.webpr.foodplanner_backend.entity.dto.response;

import java.util.UUID;

public record PantryResponseDTO(
        UUID id,
        IngredientResponseDTO ingredient
) {}
