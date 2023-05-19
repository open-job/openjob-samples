package io.openjob.samples.spring.boot.controller;

import io.openjob.samples.spring.boot.service.DelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@RestController
public class TestController {

    private final DelayService delayService;

    @Autowired
    public TestController(DelayService delayService) {
        this.delayService = delayService;
    }

    /**
     * Delay test
     *
     * @return String
     */
    @GetMapping("/test/index")
    public String index() {
        String taskId = this.delayService.send();
        return String.format("Delay taskId:%s", taskId);
    }
}
