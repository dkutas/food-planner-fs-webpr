package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.service.PantryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pantry")
@RequiredArgsConstructor
@Tag(name = "Pantry")
public class PantryController {

    private final PantryService pantryService;
}
