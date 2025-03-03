package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.dao.PantryDAO;
import com.fs.webpr.foodplanner_backend.entity.dto.PantryDTO;
import com.fs.webpr.foodplanner_backend.service.PantryService;
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
    public List<PantryDAO> getAll() {
        try {
            return pantryService.getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @PostMapping
    public PantryDAO add(PantryDTO pantryDTO) {
        try {
            return pantryService.add(pantryDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @GetMapping("/{id}")
    public PantryDAO get(@PathVariable UUID id) {
        try {
            return pantryService.get(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @PatchMapping("/{id}")
    public PantryDAO update(@PathVariable UUID id, PantryDTO pantryDTO) {
        try {
            return pantryService.update(id, pantryDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        try {
            pantryService.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }
}
