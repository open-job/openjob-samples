package io.openjob.samples.spring.boot.service.impl;

import io.openjob.common.util.DateUtil;
import io.openjob.samples.spring.boot.service.DelayService;
import io.openjob.worker.delay.DelayMessage;
import io.openjob.worker.delay.OpenjobDelayTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.1
 */
@Service
public class DelayServiceImpl implements DelayService {
    private final OpenjobDelayTemplate openjobDelayTemplate;

    @Autowired
    public DelayServiceImpl(OpenjobDelayTemplate openjobDelayTemplate) {
        this.openjobDelayTemplate = openjobDelayTemplate;
    }

    @Override
    public String send() {
        DelayMessage delayMessage = new DelayMessage();
        delayMessage.setTopic("openjob.test");
        delayMessage.setParams("params");
        delayMessage.setExtra("extra params");
        delayMessage.setExecuteTime(DateUtil.timestamp());
        return this.openjobDelayTemplate.send(delayMessage);
    }
}