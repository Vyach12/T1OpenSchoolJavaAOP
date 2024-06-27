package openschool.java.aop.service;

import openschool.java.aop.annotation.TrackAsyncTime;
import openschool.java.aop.annotation.TrackTime;
import org.springframework.aop.framework.AopContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    @TrackTime
    public void spendTime() throws InterruptedException {
        Thread.sleep(5000);
    }

    public void tempSpendTime() throws InterruptedException {
        ((ExampleService) AopContext.currentProxy()).spendTime();
    }

    @TrackAsyncTime
    @Async
    public void spendAsyncTime() throws InterruptedException {
        Thread.sleep(5000);
    }
}
