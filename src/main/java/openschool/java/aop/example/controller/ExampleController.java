package openschool.java.aop.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import openschool.java.aop.example.service.ExampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/example")
@RequiredArgsConstructor
@Tag(name = "ExampleController", description = "Тестовый контроллер для вызова методов, помеченных аннотациями TrackTime и TrackAsyncTime")
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/sync")
    @Operation(summary = "Вызывает метод, помеченный аннотацией TrackTime")
    public void sync() {
        exampleService.spendTime();
    }

    @GetMapping("/async")
    @Operation(summary = "Вызывает метод, помеченный аннотацией TrackAsyncTime")
    public void async() {
        exampleService.spendAsyncTime();
    }
}
