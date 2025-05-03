package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.dto.request.ShoppingListRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.ShoppingListResponseDTO;
import com.fs.webpr.foodplanner_backend.service.ShoppingListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public List<ShoppingListResponseDTO> getAll() {
        return shoppingListService.getAll();
    }

    @PostMapping
    @Operation(
            operationId = "addShoppingList",
            summary = "Creates a new shopping list record"
    )
    public ShoppingListResponseDTO add(@RequestBody ShoppingListRequestDTO shoppingListRequestDTO) {
        return shoppingListService.add(shoppingListRequestDTO);
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getShoppingList",
            summary = "Retrieves a shopping list record by id"
    )
    public ShoppingListResponseDTO get(@PathVariable UUID id) {
        return shoppingListService.get(id);
    }

    @GetMapping("/ingredient/{ingredientId}")
    @Operation(
            operationId = "getShoppingListByIngredientId",
            summary = "Retrieves a shopping list record by ingredient id"
    )
    public ShoppingListResponseDTO getByIngredientId(@PathVariable UUID ingredientId) {
        return shoppingListService.getByIngredientId(ingredientId);
    }

    @PatchMapping("/{id}")
    @Operation(
            operationId = "updateShoppingList",
            summary = "Updates a shopping list record by id"
    )
    public ShoppingListResponseDTO update(@PathVariable UUID id, @RequestBody ShoppingListRequestDTO shoppingListRequestDTO) {
        return shoppingListService.update(id, shoppingListRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteShoppingList",
            summary = "Deletes a shopping list record by id"
    )
    public void delete(@PathVariable UUID id) {
        shoppingListService.delete(id);
    }

    @DeleteMapping("/ingredient/{ingredientId}")
    @Operation(
            operationId = "deleteShoppingListByIngredientId",
            summary = "Deletes a shopping list record by ingredient id"
    )
    public void deleteByIngredientId(@PathVariable UUID ingredientId) {
        shoppingListService.deleteByIngredientId(ingredientId);
    }


}
