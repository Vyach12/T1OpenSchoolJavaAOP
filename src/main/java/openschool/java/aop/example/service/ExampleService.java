package openschool.java.aop.example.service;

import lombok.SneakyThrows;
import openschool.java.aop.executiontime.aspect.annotation.TrackAsyncTime;
import openschool.java.aop.executiontime.aspect.annotation.TrackTime;
import org.springframework.aop.framework.AopContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class ExampleService {

    @TrackTime
    @SneakyThrows
    public void spendTime() {
        Thread.sleep(1000);
    }

    public void tempSpendTime() {
        ((ExampleService) AopContext.currentProxy()).spendTime();
    }

    @TrackAsyncTime
    @Async
    @SneakyThrows
    public void spendAsyncTime(){
        Thread.sleep(1000);
    }
}
