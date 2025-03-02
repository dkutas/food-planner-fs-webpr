package com.fs.webpr.foodplanner_backend.repository;

import com.fs.webpr.foodplanner_backend.entity.model.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PantryRepository extends JpaRepository<Pantry, UUID> {
}
