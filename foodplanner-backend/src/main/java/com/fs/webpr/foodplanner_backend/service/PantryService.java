package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dao.PantryDAO;
import com.fs.webpr.foodplanner_backend.entity.mapper.PantryMapper;
import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.model.Pantry;
import com.fs.webpr.foodplanner_backend.entity.dto.PantryDTO;
import com.fs.webpr.foodplanner_backend.repository.IngredientRepository;
import com.fs.webpr.foodplanner_backend.repository.PantryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PantryService {

    private final PantryRepository pantryRepository;
    private final IngredientRepository ingredientRepository;
    private final PantryMapper pantryMapper;

    public List<PantryDAO> getAll() {
        List<Pantry> pantries = pantryRepository.findAll();

        return pantries.stream().map(pantryMapper::toDAO).toList();
    }

    public PantryDAO add(PantryDTO pantryDTO) {
        Pantry pantry = pantryMapper.toPantry(pantryDTO);

        UUID ingredientId = pantryDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new RuntimeException("Ingredient not found with id " + ingredientId)
        );

        pantry.setIngredient(ingredient);

        pantry = pantryRepository.save(pantry);

        return pantryMapper.toDAO(pantry);
    }

    public PantryDAO get(UUID id) {
        Pantry pantry = pantryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Pantry not found with id " + id)
        );

        return pantryMapper.toDAO(pantry);
    }

    public PantryDAO update(UUID id, PantryDTO pantryDTO) {
        Pantry pantry = pantryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Pantry not found with id " + id)
        );

        UUID ingredientId = pantryDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new RuntimeException("Ingredient not found with id " + ingredientId)
        );

        pantry.setIngredient(ingredient);

        pantry = pantryRepository.save(pantry);

        return pantryMapper.toDAO(pantry);
    }

    public void delete(UUID id) {
        pantryRepository.deleteById(id);
    }
}
