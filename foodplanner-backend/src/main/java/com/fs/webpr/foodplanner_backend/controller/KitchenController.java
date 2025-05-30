package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.entity.dto.response.KitchenResponseDTO;
import com.fs.webpr.foodplanner_backend.service.KitchenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kitchen")
@RequiredArgsConstructor
@Tag(name = "Kitchen")
public class KitchenController {

    private final KitchenService kitchenService;

    @GetMapping
    @Operation(
            operationId = "getAllKitchen",
            summary = "Retrieves a list of all kitchen"
    )
    public List<KitchenResponseDTO> getAll() {
        return kitchenService.getAll();
    }
}
