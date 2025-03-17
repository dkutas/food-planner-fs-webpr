package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.model.Kitchen;
import com.fs.webpr.foodplanner_backend.service.KitchenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/kitchen")
@RequiredArgsConstructor
@Tag(name = "Kitchen")
public class KitchenController {

    private final KitchenService kitchenService;

    @GetMapping
    public List<Kitchen> getAll() {
        try {
            return kitchenService.getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred", e);
        }
    }
}
