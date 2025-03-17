package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.dto.ShoppingListDTO;
import com.fs.webpr.foodplanner_backend.entity.model.ShoppingList;
import com.fs.webpr.foodplanner_backend.service.ShoppingListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shoppinglist")
@RequiredArgsConstructor
@Tag(name = "Shopping List")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping
    @Operation(
            operationId = "getAllShoppingLists",
            summary = "Retrieves all shopping list records"
    )
    public List<ShoppingList> getAll() {
        try {
            return shoppingListService.getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @PostMapping
    @Operation(
            operationId = "addShoppingList",
            summary = "Creates a new shopping list record"
    )
    public ShoppingList add(ShoppingListDTO shoppingListDTO) {
        try {
            return shoppingListService.add(shoppingListDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getShoppingList",
            summary = "Retrieves a shopping list record by id"
    )
    public ShoppingList get(@PathVariable UUID id) {
        try {
            return shoppingListService.get(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @PatchMapping("/{id}")
    @Operation(
            operationId = "updateShoppingList",
            summary = "Updates a shopping list record by id"
    )
    public ShoppingList update(@PathVariable UUID id, ShoppingListDTO shoppingListDTO) {
        try {
            return shoppingListService.update(id, shoppingListDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteShoppingList",
            summary = "Deletes a shopping list record by id"
    )
    public void delete(@PathVariable UUID id) {
        try {
            shoppingListService.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }
}
