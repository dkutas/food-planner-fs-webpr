package com.fs.webpr.foodplanner_backend.entity.dao;

import com.fs.webpr.foodplanner_backend.entity.model.IngredientCategory;
import lombok.Data;

import java.util.UUID;

@Data
public class IngredientDAO {
    private UUID id;
    private String name;
    private IngredientCategory category;
}
