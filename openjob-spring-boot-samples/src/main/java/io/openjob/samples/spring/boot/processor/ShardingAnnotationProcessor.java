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
public class ShardingAnnotationProcessor {
    private static final Logger logger = LoggerFactory.getLogger("openjob");

    @Openjob("annotationShardingProcessor")
    public ProcessResult shardingProcessor(JobContext jobContext) throws InterruptedException {
        logger.info("Sharding annotation processor execute success! shardingId={} shardingNum={} shardingParams={}",
                jobContext.getShardingId(), jobContext.getShardingNum(), jobContext.getShardingParam());
        Thread.sleep(30000L);
        logger.info("jobContext={}", jobContext);
        return ProcessResult.success();
    }
}
