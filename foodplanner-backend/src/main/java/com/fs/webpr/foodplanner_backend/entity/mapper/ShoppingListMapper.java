package com.fs.webpr.foodplanner_backend.entity.mapper;

import com.fs.webpr.foodplanner_backend.entity.dao.ShoppingListDAO;
import com.fs.webpr.foodplanner_backend.entity.model.ShoppingList;
import com.fs.webpr.foodplanner_backend.entity.dto.ShoppingListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShoppingListMapper {

    ShoppingList toShoppingList(ShoppingListDTO shoppingListDTO);

    ShoppingListDAO toDAO(ShoppingList shoppingList);
}
