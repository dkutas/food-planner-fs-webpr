package com.fs.webpr.foodplanner_backend.entity.dao;

import lombok.Data;

import java.util.UUID;

@Data
public class RecipeDAO {
    private UUID id;
    private String name;
    private String description;
    private KitchenDAO kitchen;
    private IngredientDAO ingredient;
}
