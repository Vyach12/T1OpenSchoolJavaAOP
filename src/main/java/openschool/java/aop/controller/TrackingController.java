package openschool.java.aop.controller;

import lombok.RequiredArgsConstructor;
import openschool.java.aop.annotation.TrackAsyncTime;
import openschool.java.aop.annotation.TrackTime;
import openschool.java.aop.service.ExampleService;
import openschool.java.aop.service.TrackingService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final ExampleService exampleService;

    @GetMapping("/statistics1")
    public String statistics() throws InterruptedException {
        exampleService.tempSpendTime();
        return "OKKKK";
    }

    @GetMapping("/statistics2")
    public String statistics2() throws InterruptedException {
        exampleService.spendAsyncTime();
        return "OKKKK2";
    }
}
