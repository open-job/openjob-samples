package io.openjob.samples.java.service.impl;

import io.openjob.common.util.DateUtil;
import io.openjob.samples.java.service.DelayService;
import io.openjob.worker.delay.DelayMessage;
import io.openjob.worker.delay.OpenjobDelayTemplate;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.1
 */
public class DelayServiceImpl implements DelayService {

    @Override
    public String send() {
        OpenjobDelayTemplate openjobDelayTemplate = new OpenjobDelayTemplate();
        DelayMessage delayMessage = new DelayMessage();
        delayMessage.setTopic("openjob.test");
        delayMessage.setParams("params");
        delayMessage.setExtra("extra params");
        delayMessage.setExecuteTime(DateUtil.timestamp());
        return openjobDelayTemplate.send(delayMessage);
    }
}