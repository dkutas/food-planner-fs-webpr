package com.fs.webpr.foodplanner_backend.repository;

import com.fs.webpr.foodplanner_backend.entity.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, UUID> {

    boolean existsByIngredient_Id(UUID ingredientId);

    void deleteByIngredient_Id(UUID ingredientId);

    Optional<ShoppingList> findByIngredient_Id(UUID ingredientId);
}
