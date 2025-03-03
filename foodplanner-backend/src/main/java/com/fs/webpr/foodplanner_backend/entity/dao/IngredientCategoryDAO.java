package com.fs.webpr.foodplanner_backend.entity.dao;

import lombok.Data;

import java.util.UUID;

@Data
public class IngredientCategoryDAO {
    private UUID id;
    private String name;
}
