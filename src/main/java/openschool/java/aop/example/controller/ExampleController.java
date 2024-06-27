package openschool.java.aop.example.controller;

import lombok.RequiredArgsConstructor;
import openschool.java.aop.example.service.ExampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/example")
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/sync")
    public void sync() {
        exampleService.spendTime();
    }


    @GetMapping("/async")
    public void async() {
        exampleService.spendAsyncTime();
    }
}
