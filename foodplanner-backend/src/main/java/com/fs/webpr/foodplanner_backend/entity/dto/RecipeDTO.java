package com.fs.webpr.foodplanner_backend.entity.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class RecipeDTO {
    private UUID id;
    private String name;
    private String description;
    private UUID kitchenId;
    private Set<UUID> ingredientIds;
}
