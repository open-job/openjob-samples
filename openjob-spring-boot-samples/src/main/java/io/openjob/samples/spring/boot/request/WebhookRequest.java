package io.openjob.samples.spring.boot.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.6
 */
@Data
public class WebhookRequest {
    @JsonProperty(value = "timestamp")
    private Long timestamp;

    @JsonProperty(value = "sign")
    private String sign;

    @JsonProperty(value = "app_name")
    private String appName;

    @JsonProperty(value = "job_name")
    private String jobName;

    /**
     * Job id or delay topic
     */
    @JsonProperty(value = "job_id")
    private String jobId;

    /**
     * Job instance id or delay task id
     */
    @JsonProperty(value = "job_instance_id")
    private String jobInstanceId;

    @JsonProperty(value = "processor")
    private String processor;

    @JsonProperty(value = "event")
    private String event;

    @JsonProperty(value = "message")
    private String message;
}
