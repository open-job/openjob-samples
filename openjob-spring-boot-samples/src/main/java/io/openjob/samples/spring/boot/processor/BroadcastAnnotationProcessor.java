package io.openjob.samples.spring.boot.processor;

import io.openjob.worker.context.JobContext;
import io.openjob.worker.processor.ProcessResult;
import io.openjob.worker.spring.boot.annotation.Openjob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.5
 */
@Component
public class BroadcastAnnotationProcessor {
    private static final Logger logger = LoggerFactory.getLogger("openjob");

    @Openjob("annotationBroadcastProcessor")
    public ProcessResult broadcastProcessor(JobContext jobContext) {
        logger.info("Broadcast annotation processor execute success! jobContext={}", jobContext);
        return ProcessResult.success();
    }
}
