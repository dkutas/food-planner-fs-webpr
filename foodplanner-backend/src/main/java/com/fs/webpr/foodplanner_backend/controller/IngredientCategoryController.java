package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.dto.response.IngredientCategoryResponseDTO;
import com.fs.webpr.foodplanner_backend.service.IngredientCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<IngredientCategoryResponseDTO> getAll() {
        return ingredientCategoryService.getAll();
    }
}
