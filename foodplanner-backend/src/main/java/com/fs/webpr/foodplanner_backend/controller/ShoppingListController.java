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
            operationId = "getAllShoppingListItem",
            summary = "Retrieves all shopping list records"
    )
    public List<ShoppingListResponseDTO> getAll() {
        return shoppingListService.getAll();
    }

    @PostMapping
    @Operation(
            operationId = "addShoppingListItem",
            summary = "Creates a new shopping list record"
    )
    public ShoppingListResponseDTO add(@RequestBody ShoppingListRequestDTO shoppingListRequestDTO) {
        return shoppingListService.add(shoppingListRequestDTO);
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getShoppingListItem",
            summary = "Retrieves a shopping list record by id"
    )
    public ShoppingListResponseDTO get(@PathVariable UUID id) {
        return shoppingListService.get(id);
    }

    @GetMapping("/ingredient/{ingredientId}")
    @Operation(
            operationId = "getAllShoppingListItemByIngredientId",
            summary = "Retrieves a shopping list record by ingredient id"
    )
    public List<ShoppingListResponseDTO> getAllByIngredientId(@PathVariable UUID ingredientId) {
        return shoppingListService.getAllByIngredientId(ingredientId);
    }

    @PatchMapping("/{id}")
    @Operation(
            operationId = "updateShoppingListItem",
            summary = "Updates a shopping list record by id"
    )
    public ShoppingListResponseDTO update(@PathVariable UUID id, @RequestBody ShoppingListRequestDTO shoppingListRequestDTO) {
        return shoppingListService.update(id, shoppingListRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteShoppingListItem",
            summary = "Deletes a shopping list record by id"
    )
    public void delete(@PathVariable UUID id) {
        shoppingListService.delete(id);
    }

}
