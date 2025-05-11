package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.common.annotation.CurrentUser;
import com.fs.webpr.foodplanner_backend.entity.dto.authentication.AuthenticatedUser;
import com.fs.webpr.foodplanner_backend.entity.dto.request.RecipeRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.RecipeResponseDTO;
import com.fs.webpr.foodplanner_backend.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name = "Recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/public")
    @Operation(
            operationId = "getAllPublicRecipe",
            summary = "Retrieves all public recipes"
    )
    public List<RecipeResponseDTO> getAllPublic() {
        return recipeService.getAllPublic();
    }

    @GetMapping
    @Operation(
            operationId = "getAllRecipe",
            summary = "Retrieves all recipes"
    )
    @PreAuthorize("isAuthenticated()")
    public List<RecipeResponseDTO> getAll(
            @CurrentUser AuthenticatedUser user
    ) {
        return recipeService.getAll(user);
    }

    @PostMapping
    @Operation(
            operationId = "addRecipe",
            summary = "Creates a new recipe"
    )
    @PreAuthorize("isAuthenticated()")
    public RecipeResponseDTO add(
            @CurrentUser AuthenticatedUser user,
            @RequestBody RecipeRequestDTO recipeRequestDTO
    ) {
        return recipeService.add(user, recipeRequestDTO);
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getRecipe",
            summary = "Retrieves a recipe by id"
    )
    @PreAuthorize("isAuthenticated()")
    public RecipeResponseDTO get(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id
    ) {
        return recipeService.get(user, id);
    }

    @PatchMapping("/{id}")
    @Operation(
            operationId = "updateRecipe",
            summary = "Updates a recipe by id"
    )
    @PreAuthorize("isAuthenticated()")
    public RecipeResponseDTO update(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id,
            @RequestBody RecipeRequestDTO recipeRequestDTO
    ) {
        return recipeService.update(user, id, recipeRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteRecipe",
            summary = "Deletes a recipe by id"
    )
    @PreAuthorize("isAuthenticated()")
    public void delete(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id
    ) {
        recipeService.delete(user, id);
    }
}
