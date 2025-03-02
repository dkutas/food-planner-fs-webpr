package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.service.RecipeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name = "Recipe")
public class RecipeController {

    private final RecipeService recipeService;
}
