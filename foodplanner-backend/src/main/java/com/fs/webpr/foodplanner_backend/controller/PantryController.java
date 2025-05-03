package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.dto.request.PantryRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.PantryResponseDTO;
import com.fs.webpr.foodplanner_backend.service.PantryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
            operationId = "getAllPantries",
            summary = "Retrieves all pantry records"
    )
    public List<PantryResponseDTO> getAll() {
        return pantryService.getAll();
    }

    @PostMapping
    @Operation(
            operationId = "addPantry",
            summary = "Creates a new pantry record"
    )
    public PantryResponseDTO add(@RequestBody PantryRequestDTO pantryRequestDTO) {
        return pantryService.add(pantryRequestDTO);
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getPantry",
            summary = "Retrieves a pantry by id"
    )
    public PantryResponseDTO get(@PathVariable UUID id) {
        return pantryService.get(id);
    }

    @GetMapping("/ingredient/{ingredientId}")
    @Operation(
            operationId = "getPantryByIngredientId",
            summary = "Retrieves a pantry by ingredient id"
    )
    public PantryResponseDTO getByIngredientId(@PathVariable UUID ingredientId) {
        return pantryService.getByIngredientId(ingredientId);
    }

    @PatchMapping("/{id}")
    @Operation(
            operationId = "updatePantry",
            summary = "Updates a pantry by id"
    )
    public PantryResponseDTO update(@PathVariable UUID id, @RequestBody PantryRequestDTO pantryRequestDTO) {
        return pantryService.update(id, pantryRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deletePantry",
            summary = "Deletes a pantry by id"
    )
    public void delete(@PathVariable UUID id) {
        pantryService.delete(id);
    }

    @DeleteMapping("/ingredient/{ingredientId}")
    @Operation(
            operationId = "deletePantryByIngredientId",
            summary = "Deletes a pantry record by ingredient id"
    )
    public void deleteByIngredientId(@PathVariable UUID ingredientId) {
        pantryService.deleteByIngredientId(ingredientId);
    }
}
