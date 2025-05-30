package com.fs.webpr.foodplanner_backend.repository;

import com.fs.webpr.foodplanner_backend.entity.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, UUID> {

    List<ShoppingList> findAllByUserId(UUID userId);

    List<ShoppingList> findAllByUserIdAndIngredient_Id(UUID userId, UUID ingredientId);

}
