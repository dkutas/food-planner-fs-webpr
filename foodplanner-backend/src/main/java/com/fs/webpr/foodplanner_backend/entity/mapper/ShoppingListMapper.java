package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.model.ShoppingList;
import com.fs.webpr.foodplanner_backend.entity.model.ShoppingListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingListMapper {

    ShoppingList toShoppingList(ShoppingListDTO shoppingListDTO);
}
