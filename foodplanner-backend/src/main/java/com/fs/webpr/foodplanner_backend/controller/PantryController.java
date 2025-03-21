package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.dto.PantryDTO;
import com.fs.webpr.foodplanner_backend.entity.model.Pantry;
import com.fs.webpr.foodplanner_backend.service.PantryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<Pantry> getAll() {
        try {
            return pantryService.getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @PostMapping
    @Operation(
            operationId = "addPantry",
            summary = "Creates a new pantry record"
    )
    public Pantry add(PantryDTO pantryDTO) {
        try {
            return pantryService.add(pantryDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getPantry",
            summary = "Retrieves a pantry by id"
    )
    public Pantry get(@PathVariable UUID id) {
        try {
            return pantryService.get(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @PatchMapping("/{id}")
    @Operation(
            operationId = "updatePantry",
            summary = "Updates a pantry by id"
    )
    public Pantry update(@PathVariable UUID id, PantryDTO pantryDTO) {
        try {
            return pantryService.update(id, pantryDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deletePantry",
            summary = "Deletes a pantry by id"
    )
    public void delete(@PathVariable UUID id) {
        try {
            pantryService.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }
}
