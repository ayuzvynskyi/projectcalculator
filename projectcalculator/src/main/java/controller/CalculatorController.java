package com.example.projectcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/calculate")
    public String calculate(
            @RequestParam(name = "num1", required = false, defaultValue = "0") double num1,
            @RequestParam(name = "num2", required = false, defaultValue = "0") double num2,
            @RequestParam(name = "operation", required = false, defaultValue = "add") String operation,
            Model model) {
        double result = 0;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    model.addAttribute("error", "Cannot divide by zero");
                }
                break;
            default:
                model.addAttribute("error", "Invalid operation");
                break;
        }
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("operation", operation);
        model.addAttribute("result", result);
        return "index";
    }
}
