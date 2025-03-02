package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/kitchen")
@RequiredArgsConstructor
public class KitchenController {

    private final KitchenService kitchenService;
}
