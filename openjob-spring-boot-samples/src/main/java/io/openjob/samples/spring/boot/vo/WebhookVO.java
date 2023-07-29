package io.openjob.samples.spring.boot.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.6
 */
@Data
@AllArgsConstructor
public class WebhookVO {
    @JsonProperty(value = "errcode")
    private Integer errCode;

    @JsonProperty(value = "errmsg")
    private String errMsg;
}
