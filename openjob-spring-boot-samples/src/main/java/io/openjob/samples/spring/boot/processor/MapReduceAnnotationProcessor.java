package io.openjob.samples.spring.boot.processor;

import com.google.common.collect.Lists;
import io.openjob.worker.context.JobContext;
import io.openjob.worker.processor.MapReduceProcessor;
import io.openjob.worker.processor.ProcessResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.5
 */
@Slf4j
@Component("annotationMapReduceProcessor")
public class MapReduceAnnotationProcessor implements MapReduceProcessor {
    private static final Logger logger = LoggerFactory.getLogger("openjob");

    private static final String TWO_NAME = "TASK_TWO";

    private static final String THREE_NAME = "TASK_THREE";

    @Override
    public ProcessResult process(JobContext context) throws Exception {
        if (context.isRoot()) {
            List<MrTaskTest> tasks = new ArrayList<>();
            for (int i = 1; i < 5; i++) {
                tasks.add(new MrTaskTest(i, Lists.newArrayList(String.valueOf(1))));
            }

            logger.info("root task taskId={} taskList={}", context.getTaskId(), tasks);
            return this.map(tasks, TWO_NAME);
        }

        if (context.isTask(TWO_NAME)) {
            MrTaskTest task = (MrTaskTest) context.getTask();
            List<MrTaskTest> tasks = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                tasks.add(new MrTaskTest(i, Lists.newArrayList(String.valueOf(task.getId() * i))));
            }

            logger.info("task two taskId={} taskList={}", context.getTaskId(), tasks);
            return this.map(tasks, THREE_NAME);
        }

        if (context.isTask(THREE_NAME)) {
            MrTaskTest task = (MrTaskTest) context.getTask();
            logger.info("task three taskId={}", context.getTaskId());
            return new ProcessResult(true, String.valueOf(task.getId() * 2));
        }

        return ProcessResult.success();
    }

    @Override
    public ProcessResult reduce(JobContext jobContext) {
        logger.info("Map reduce taskName={} taskResult={}", jobContext.getTaskName(), jobContext.getTaskResultList());
        return ProcessResult.success();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MrTaskTest {
        private Integer id;
        private List<String> names;
    }
}
