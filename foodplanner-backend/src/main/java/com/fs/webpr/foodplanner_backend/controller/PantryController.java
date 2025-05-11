package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.common.annotation.CurrentUser;
import com.fs.webpr.foodplanner_backend.entity.dto.authentication.AuthenticatedUser;
import com.fs.webpr.foodplanner_backend.entity.dto.request.PantryRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.PantryResponseDTO;
import com.fs.webpr.foodplanner_backend.service.PantryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pantry")
@RequiredArgsConstructor
@Tag(name = "Pantry")
public class PantryController {

    private final PantryService pantryService;

    @GetMapping
    @Operation(
            operationId = "getAllPantryItem",
            summary = "Retrieves all pantry records"
    )
    @PreAuthorize("isAuthenticated()")
    public List<PantryResponseDTO> getAll(
            @CurrentUser AuthenticatedUser user
    ) {
        return pantryService.getAll(user);
    }

    @PostMapping
    @Operation(
            operationId = "addPantryItem",
            summary = "Creates a new pantry record"
    )
    @PreAuthorize("isAuthenticated()")
    public PantryResponseDTO add(
            @CurrentUser AuthenticatedUser user,
            @RequestBody PantryRequestDTO pantryRequestDTO
    ) {
        return pantryService.add(user, pantryRequestDTO);
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getPantryItem",
            summary = "Retrieves a pantry by id"
    )
    @PreAuthorize("isAuthenticated()")
    public PantryResponseDTO get(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id
    ) {
        return pantryService.get(user, id);
    }

    @GetMapping("/ingredient/{ingredientId}")
    @Operation(
            operationId = "getAllPantryItemByIngredientId",
            summary = "Retrieves a pantry by ingredient id"
    )
    @PreAuthorize("isAuthenticated()")
    public List<PantryResponseDTO> getAllByIngredientId(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID ingredientId
    ) {
        return pantryService.getAllByIngredientId(user, ingredientId);
    }

    @PatchMapping("/{id}")
    @Operation(
            operationId = "updatePantryItem",
            summary = "Updates a pantry by id"
    )
    @PreAuthorize("isAuthenticated()")
    public PantryResponseDTO update(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id,
            @RequestBody PantryRequestDTO pantryRequestDTO
    ) {
        return pantryService.update(user, id, pantryRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deletePantryItem",
            summary = "Deletes a pantry by id"
    )
    @PreAuthorize("isAuthenticated()")
    public void delete(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id
    ) {
        pantryService.delete(user, id);
    }

}
