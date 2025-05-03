package com.fs.webpr.foodplanner_backend.entity.dto.request;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class MealPlanRequestDTO {
    private UUID recipeId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
}
