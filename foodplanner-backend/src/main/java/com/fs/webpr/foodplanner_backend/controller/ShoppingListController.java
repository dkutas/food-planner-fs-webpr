package com.fs.webpr.foodplanner_backend.controller;

import com.fs.webpr.foodplanner_backend.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/shoppinglist")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;
}
