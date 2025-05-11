package com.fs.webpr.foodplanner_backend.entity.dto.request;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class RecipeRequestDTO {
    private String name;
    private String description;
    private Boolean isPublic;
    private UUID kitchenId;
    private Set<UUID> ingredientIds;
}
