package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.service.IngredientCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredient/category")
@RequiredArgsConstructor
public class IngredientCategoryController {

    private final IngredientCategoryService ingredientCategoryService;
}
