package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.service.KitchenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kitchen")
@RequiredArgsConstructor
@Tag(name = "Kitchen")
public class KitchenController {

    private final KitchenService kitchenService;
}
