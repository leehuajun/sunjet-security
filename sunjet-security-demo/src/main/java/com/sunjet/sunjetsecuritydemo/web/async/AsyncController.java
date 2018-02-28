package com.sunjet.sunjetsecuritydemo.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 同步处理
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/order")
    public String order() throws InterruptedException {
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程返回");
        return "success";
    }

    /**
     * 使用 Runnable 异步处理 Rest 服务
     * 副线程由主线程发起的
     * @return
     */
    @GetMapping("/orderAsync")
    public Callable<String> orderAsync(){
        logger.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return "success";
            }
        };
        logger.info("主线程返回");
        return result;
    }

    @GetMapping("/orderDeferred")
    public DeferredResult<String> orderDeferred() throws Exception {
        logger.info("主线程开始");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber,result );

        logger.info("主线程返回");
        return result;
    }
}
