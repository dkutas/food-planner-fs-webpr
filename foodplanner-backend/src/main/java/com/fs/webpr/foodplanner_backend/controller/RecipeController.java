package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.dto.RecipeDTO;
import com.fs.webpr.foodplanner_backend.entity.model.Recipe;
import com.fs.webpr.foodplanner_backend.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name = "Recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    @Operation(
            operationId = "getAllRecipes",
            summary = "Retrieves all recipes"
    )
    public List<Recipe> getAll() {
        try {
            return recipeService.getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @PostMapping
    @Operation(
            operationId = "addRecipe",
            summary = "Creates a new recipe"
    )
    public Recipe add(RecipeDTO recipeDTO) {
        try {
            return recipeService.add(recipeDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getRecipe",
            summary = "Retrieves a recipe by id"
    )
    public Recipe get(@PathVariable UUID id) {
        try {
            return recipeService.get(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @PatchMapping("/{id}")
    @Operation(
            operationId = "updateRecipe",
            summary = "Updates a recipe by id"
    )
    public Recipe update(@PathVariable UUID id, RecipeDTO recipeDTO) {
        try {
            return recipeService.update(id, recipeDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteRecipe",
            summary = "Deletes a recipe by id"
    )
    public void delete(@PathVariable UUID id) {
        try {
            recipeService.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }
}
