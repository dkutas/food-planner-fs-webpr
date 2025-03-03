package com.fs.webpr.foodplanner_backend.entity.dao;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class MealPlanDAO {
    private UUID id;
    private RecipeDAO recipe;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
}
