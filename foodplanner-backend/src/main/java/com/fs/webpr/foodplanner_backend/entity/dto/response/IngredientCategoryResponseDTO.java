package com.fs.webpr.foodplanner_backend.entity.dto.response;

import java.util.UUID;

public record IngredientCategoryResponseDTO(
        UUID id,
        String name
) {}
