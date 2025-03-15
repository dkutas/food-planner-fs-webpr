package com.fs.webpr.foodplanner_backend.entity.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class MealPlanDTO {
    private UUID recipeId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
}
