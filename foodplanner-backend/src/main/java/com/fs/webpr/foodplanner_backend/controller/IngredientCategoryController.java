package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.model.IngredientCategoryDAO;
import com.fs.webpr.foodplanner_backend.service.IngredientCategoryService;
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
    public List<IngredientCategoryDAO> getAll() {
        return ingredientCategoryService.getAll();
    }
}
