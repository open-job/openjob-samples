package io.openjob.samples.spring.boot.controller;

import io.openjob.common.util.DateUtil;
import io.openjob.worker.delay.DelayMessage;
import io.openjob.worker.delay.DelayTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@RestController
public class TestController {
    private final DelayTemplate delayTemplate;

    @Autowired
    public TestController(DelayTemplate delayTemplate) {
        this.delayTemplate = delayTemplate;
    }

    /**
     * Delay test
     *
     * @return String
     */
    @GetMapping("/test/index")
    public String index() {
        DelayMessage delayMessage = new DelayMessage();
        delayMessage.setTopic("openjob.test");
        delayMessage.setExecuteTime(DateUtil.timestamp());
        String taskId = this.delayTemplate.send(delayMessage);
        return String.format("Delay taskId:%s", taskId);
    }
}
