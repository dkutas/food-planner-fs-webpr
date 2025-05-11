package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.common.annotation.CurrentUser;
import com.fs.webpr.foodplanner_backend.entity.dto.authentication.AuthenticatedUser;
import com.fs.webpr.foodplanner_backend.entity.dto.request.ShoppingListRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.ShoppingListResponseDTO;
import com.fs.webpr.foodplanner_backend.service.ShoppingListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("isAuthenticated()")
    public List<ShoppingListResponseDTO> getAll(
            @CurrentUser AuthenticatedUser user
    ) {
        return shoppingListService.getAll(user);
    }

    @PostMapping
    @Operation(
            operationId = "addShoppingListItem",
            summary = "Creates a new shopping list record"
    )
    @PreAuthorize("isAuthenticated()")
    public ShoppingListResponseDTO add(
            @CurrentUser AuthenticatedUser user,
            @RequestBody ShoppingListRequestDTO shoppingListRequestDTO
    ) {
        return shoppingListService.add(user, shoppingListRequestDTO);
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getShoppingListItem",
            summary = "Retrieves a shopping list record by id"
    )
    @PreAuthorize("isAuthenticated()")
    public ShoppingListResponseDTO get(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id
    ) {
        return shoppingListService.get(user, id);
    }

    @GetMapping("/ingredient/{ingredientId}")
    @Operation(
            operationId = "getAllShoppingListItemByIngredientId",
            summary = "Retrieves a shopping list record by ingredient id"
    )
    @PreAuthorize("isAuthenticated()")
    public List<ShoppingListResponseDTO> getAllByIngredientId(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID ingredientId
    ) {
        return shoppingListService.getAllByIngredientId(user, ingredientId);
    }

    @PatchMapping("/{id}")
    @Operation(
            operationId = "updateShoppingListItem",
            summary = "Updates a shopping list record by id"
    )
    @PreAuthorize("isAuthenticated()")
    public ShoppingListResponseDTO update(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id,
            @RequestBody ShoppingListRequestDTO shoppingListRequestDTO
    ) {
        return shoppingListService.update(user, id, shoppingListRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteShoppingListItem",
            summary = "Deletes a shopping list record by id"
    )
    @PreAuthorize("isAuthenticated()")
    public void delete(
            @CurrentUser AuthenticatedUser user,
            @PathVariable UUID id
    ) {
        shoppingListService.delete(user, id);
    }

}
