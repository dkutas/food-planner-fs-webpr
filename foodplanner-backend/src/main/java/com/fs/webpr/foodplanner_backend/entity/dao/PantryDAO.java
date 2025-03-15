package com.fs.webpr.foodplanner_backend.entity.dao;

import lombok.Data;

import java.util.UUID;

@Data
public class PantryDAO {
    private UUID id;
    private IngredientDAO ingredient;
}
