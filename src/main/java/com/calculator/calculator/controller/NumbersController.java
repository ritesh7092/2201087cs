package com.calculator.calculator.controller;

import com.calculator.calculator.service.AverageService;
import com.calculator.calculator.storage.InMemoryStore;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/numbers")
public class NumbersController {

    private final AverageService averageService;

    public NumbersController(AverageService averageService) {
        this.averageService = averageService;
    }

    @GetMapping("/{numberId}")
    public Map<String, Object> getNumbers(@PathVariable("numberId") String numberId) {
        return averageService.processRequest(numberId);
    }
}
