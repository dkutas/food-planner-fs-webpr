package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.dto.request.ShoppingListRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.ShoppingListResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.model.ShoppingList;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShoppingListMapper {

    ShoppingList toShoppingList(ShoppingListRequestDTO shoppingListRequestDTO);

    ShoppingListResponseDTO toShoppingListResponseDTO(ShoppingList shoppingList);

    List<ShoppingListResponseDTO> toShoppingListResponseDTO(List<ShoppingList> shoppingLists);
}
