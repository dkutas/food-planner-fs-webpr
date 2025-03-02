package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.model.Recipe;
import com.fs.webpr.foodplanner_backend.entity.model.RecipeDTO;
import com.fs.webpr.foodplanner_backend.service.RecipeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name = "Recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public Recipe add(RecipeDTO recipeDTO) {
        try {
            return recipeService.add(recipeDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }
}
