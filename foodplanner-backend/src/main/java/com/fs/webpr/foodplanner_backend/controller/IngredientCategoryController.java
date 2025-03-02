package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.service.IngredientCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredient/category")
@RequiredArgsConstructor
@Tag(name = "Ingredient Category")
public class IngredientCategoryController {

    private final IngredientCategoryService ingredientCategoryService;
}
