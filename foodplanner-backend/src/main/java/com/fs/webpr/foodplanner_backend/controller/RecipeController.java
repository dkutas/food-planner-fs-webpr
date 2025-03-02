package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
}
