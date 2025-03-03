package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.model.Ingredient;
import com.fs.webpr.foodplanner_backend.entity.model.IngredientDAO;
import com.fs.webpr.foodplanner_backend.service.IngredientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@Tag(name = "Ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public List<IngredientDAO> getAll() {
        return ingredientService.getAll();
    }


}
