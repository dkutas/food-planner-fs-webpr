package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.service.MealPlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mealplan")
@RequiredArgsConstructor
@Tag(name = "Meal Plan")
public class MealPlanController {

    private final MealPlanService mealPlanService;
}
