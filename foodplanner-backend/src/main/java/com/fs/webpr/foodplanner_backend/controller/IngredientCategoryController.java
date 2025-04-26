package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.model.IngredientCategory;
import com.fs.webpr.foodplanner_backend.service.IngredientCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/ingredient/category")
@RequiredArgsConstructor
@Tag(name = "Ingredient Category")
public class IngredientCategoryController {

    private final IngredientCategoryService ingredientCategoryService;

    @GetMapping
    @Operation(
            operationId = "getAllIngredientCategories",
            summary = "Retrieves a list of all ingredient categories"
    )
    public List<IngredientCategory> getAll() {
        try {
            return ingredientCategoryService.getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred");
        }
    }
}
