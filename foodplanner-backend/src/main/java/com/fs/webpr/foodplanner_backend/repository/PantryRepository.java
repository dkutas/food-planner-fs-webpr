package com.fs.webpr.foodplanner_backend.repository;

import com.fs.webpr.foodplanner_backend.entity.model.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PantryRepository extends JpaRepository<Pantry, UUID> {

    boolean existsByIngredient_Id(UUID ingredientId);

    void deleteByIngredient_Id(UUID ingredientId);

    Optional<Pantry> findByIngredient_Id(UUID ingredientId);

    List<Pantry> findAllByUserId(UUID userId);

}
