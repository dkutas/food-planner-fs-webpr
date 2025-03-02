package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.service.PantryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pantry")
@RequiredArgsConstructor
public class PantryController {

    private final PantryService pantryService;
}
