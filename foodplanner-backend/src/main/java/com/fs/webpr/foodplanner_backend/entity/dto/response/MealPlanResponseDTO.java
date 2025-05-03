package com.fs.webpr.foodplanner_backend.entity.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

public record MealPlanResponseDTO(
        UUID id,
        RecipeResponseDTO recipe,
        OffsetDateTime startDate,
        OffsetDateTime endDate
) {
}
