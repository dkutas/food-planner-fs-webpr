package com.fs.webpr.foodplanner_backend.service;

import com.fs.webpr.foodplanner_backend.entity.dto.authentication.AuthenticatedUser;
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
import org.springframework.security.access.AccessDeniedException;
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

    public List<PantryResponseDTO> getAll(AuthenticatedUser user) {
        return pantryMapper.toPantryResponseDTO(pantryRepository.findAllByUserId(user.userId()));
    }

    public PantryResponseDTO add(AuthenticatedUser user, PantryRequestDTO pantryRequestDTO) {
        UUID ingredientId = pantryRequestDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new ResourceNotFoundException("Ingredient not found with id " + ingredientId)
        );

        Pantry pantry = new Pantry();

        pantry.setUserId(user.userId());
        pantry.setIngredient(ingredient);

        return pantryMapper.toPantryResponseDTO(pantryRepository.save(pantry));
    }

    public PantryResponseDTO get(AuthenticatedUser user, UUID id) {
        Pantry pantry = pantryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pantry not found with id " + id)
        );

        if (pantry.getUserId() != user.userId()) {
            throw new AccessDeniedException("You do not have permission to get pantry item with id " + id);
        }

        return pantryMapper.toPantryResponseDTO(pantry);
    }

    public PantryResponseDTO getByIngredientId(AuthenticatedUser user, UUID ingredientId) {
        Pantry pantry = pantryRepository.findByIngredient_Id(ingredientId).orElseThrow(
                () -> new ResourceNotFoundException("Pantry not found with ingredient id " + ingredientId)
        );

        if (pantry.getUserId() != user.userId()) {
            throw new AccessDeniedException("You do not have permission to get pantry item with ingredient id " + ingredientId);
        }

        return pantryMapper.toPantryResponseDTO(pantry);
    }

    public PantryResponseDTO update(AuthenticatedUser user, UUID id, PantryRequestDTO pantryRequestDTO) {
        Pantry pantry = pantryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pantry not found with id " + id)
        );

        if (pantry.getUserId() != user.userId()) {
            throw new AccessDeniedException("You do not have permission to update pantry item with id " + id);
        }

        UUID ingredientId = pantryRequestDTO.getIngredientId();

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
                () -> new ResourceNotFoundException("Ingredient not found with id " + ingredientId)
        );

        pantry.setIngredient(ingredient);

        return pantryMapper.toPantryResponseDTO(pantryRepository.save(pantry));
    }

    public void delete(AuthenticatedUser user, UUID id) {
        Pantry pantry = pantryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pantry Item not found with id " + id)
        );

        if (pantry.getUserId() != user.userId()) {
            throw new AccessDeniedException("You do not have permission to delete pantry item with id " + id);
        }

        pantryRepository.deleteById(id);
    }

}
