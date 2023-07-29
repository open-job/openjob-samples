package io.openjob.samples.spring.boot.controller;

import io.openjob.samples.spring.boot.request.WebhookRequest;
import io.openjob.samples.spring.boot.service.DelayService;
import io.openjob.samples.spring.boot.vo.WebhookVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Slf4j
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

    @PostMapping("/test/webhook")
    public WebhookVO webhook(@RequestBody WebhookRequest webhookRequest) {
        String secret = "SyE7w&@!6bcOupy@";
        if (this.getSign(secret, webhookRequest.getTimestamp()).equals(webhookRequest.getSign())) {
            log.info("webhook success! {}", webhookRequest);
            return new WebhookVO(0, "ok");
        }
        log.error("webhook failed! {}", webhookRequest);
        return new WebhookVO(1, "sign error");
    }

    protected String getSign(String secret, Long timestamp) {
        String signStr = String.format("%s\n%s", timestamp, secret);
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(signStr.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(new byte[]{});
            return new String(Base64.encodeBase64(signData));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
