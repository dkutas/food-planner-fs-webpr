package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.dto.request.RecipeRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.RecipeResponseDTO;
import com.fs.webpr.foodplanner_backend.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public List<RecipeResponseDTO> getAll() {
        return recipeService.getAll();
    }

    @PostMapping
    @Operation(
            operationId = "addRecipe",
            summary = "Creates a new recipe"
    )
    public RecipeResponseDTO add(@RequestBody RecipeRequestDTO recipeRequestDTO) {
        return recipeService.add(recipeRequestDTO);
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getRecipe",
            summary = "Retrieves a recipe by id"
    )
    public RecipeResponseDTO get(@PathVariable UUID id) {
        return recipeService.get(id);
    }

    @PatchMapping("/{id}")
    @Operation(
            operationId = "updateRecipe",
            summary = "Updates a recipe by id"
    )
    public RecipeResponseDTO update(@PathVariable UUID id, @RequestBody RecipeRequestDTO recipeRequestDTO) {
        return recipeService.update(id, recipeRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteRecipe",
            summary = "Deletes a recipe by id"
    )
    public void delete(@PathVariable UUID id) {
        recipeService.delete(id);
    }
}
