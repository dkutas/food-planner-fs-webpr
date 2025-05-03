package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dto.request.PantryRequestDTO;
import com.fs.webpr.foodplanner_backend.entity.dto.response.PantryResponseDTO;
import com.fs.webpr.foodplanner_backend.entity.mapper.PantryMapper;
import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.model.Pantry;
import com.fs.webpr.foodplanner_backend.exception.AlreadyExistsException;
import com.fs.webpr.foodplanner_backend.exception.ResourceNotFoundException;
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
@SuppressWarnings("Duplicates")
public class PantryService {

    private final PantryRepository pantryRepository;
    private final IngredientRepository ingredientRepository;
    private final PantryMapper pantryMapper;

    public List<PantryResponseDTO> getAll() {
        return pantryMapper.toPantryResponseDTO(pantryRepository.findAll());
    }

    public PantryResponseDTO add(PantryRequestDTO pantryRequestDTO) {
        UUID ingredientId = pantryRequestDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new ResourceNotFoundException("Ingredient not found with id " + ingredientId)
        );

        boolean isPantryIngredientAlreadyExists = pantryRepository.existsByIngredient_Id(ingredientId);

        if (isPantryIngredientAlreadyExists) {
            throw new AlreadyExistsException("Ingredient with id " + ingredientId + "is already in pantry");
        }

        Pantry pantry = new Pantry();

        pantry.setIngredient(ingredient);

        return pantryMapper.toPantryResponseDTO(pantryRepository.save(pantry));
    }

    public PantryResponseDTO get(UUID id) {
        return pantryMapper.toPantryResponseDTO(pantryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pantry not found with id " + id)
        ));
    }

    public PantryResponseDTO getByIngredientId(UUID ingredientId) {
        return pantryMapper.toPantryResponseDTO(pantryRepository.findByIngredient_Id(ingredientId).orElseThrow(
                () -> new ResourceNotFoundException("Pantry not found with ingredient id " + ingredientId)
        ));
    }

    public PantryResponseDTO update(UUID id, PantryRequestDTO pantryRequestDTO) {
        Pantry pantry = pantryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pantry not found with id " + id)
        );

        UUID ingredientId = pantryRequestDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new ResourceNotFoundException("Ingredient not found with id " + ingredientId)
        );

        boolean isPantryIngredientAlreadyExists = pantryRepository.existsByIngredient_Id(ingredientId);

        if (isPantryIngredientAlreadyExists) {
            throw new AlreadyExistsException("Ingredient with id " + ingredientId + "is already in pantry");
        }

        pantry.setIngredient(ingredient);

        return pantryMapper.toPantryResponseDTO(pantryRepository.save(pantry));
    }

    public void delete(UUID id) {
        boolean isShoppingListItemExists = pantryRepository.existsById(id);

        if (!isShoppingListItemExists) {
            throw new ResourceNotFoundException("Pantry Item not found with id " + id);
        }

        pantryRepository.deleteById(id);
    }

    public void deleteByIngredientId(UUID ingredientId) {

        boolean isShoppingListIngredientExists = pantryRepository.existsByIngredient_Id(ingredientId);

        if (!isShoppingListIngredientExists) {
            throw new ResourceNotFoundException("Pantry Ingredient not found with id " + ingredientId);
        }

        pantryRepository.deleteByIngredient_Id(ingredientId);
    }
}
