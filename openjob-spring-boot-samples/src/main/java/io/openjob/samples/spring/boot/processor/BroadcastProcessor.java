package io.openjob.samples.spring.boot.processor;

import io.openjob.worker.context.JobContext;
import io.openjob.worker.processor.JavaProcessor;
import io.openjob.worker.processor.ProcessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.7
 */
@Component("broadcastPostProcessor")
public class BroadcastProcessor implements JavaProcessor {
    private static final Logger logger = LoggerFactory.getLogger("openjob");

    @Override
    public void preProcess(JobContext context) {
        logger.info("Broadcast pre process!");
    }

    @Override
    public ProcessResult process(JobContext context) throws Exception {
        logger.info("Broadcast process!");
        return new ProcessResult(true, "{\"data\":\"result data\"}");
    }

    @Override
    public ProcessResult postProcess(JobContext context) {
        logger.info("Broadcast post process taskList={}", context.getTaskResultList());
        System.out.println(context.getTaskResultList());
        return ProcessResult.success();
    }
}
