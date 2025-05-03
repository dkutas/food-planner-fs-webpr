package com.fs.webpr.foodplanner_backend.entity.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class ShoppingListRequestDTO {
    private UUID ingredientId;
}
