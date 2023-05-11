package io.openjob.samples.java.processor;

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

import java.util.ArrayList;
import java.util.List;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Slf4j
public class MapReduceProcessorSample implements MapReduceProcessor {
    private static final Logger logger = LoggerFactory.getLogger("openjob");

    private static final Integer FIRST_COUNT = 1001;

    private static final Integer SECOND_COUNT = 21;

    private static final String TWO_NAME = "TASK_TWO";

    private static final String THREE_NAME = "TASK_THREE";

    @Override
    public ProcessResult process(JobContext context) {
        if (context.isRoot()) {
            List<MrTaskTest> tasks = new ArrayList<>();
            for (int i = 1; i < FIRST_COUNT; i++) {
                System.out.println("root" + i);
                tasks.add(new MrTaskTest(i, Lists.newArrayList(String.valueOf(1))));
            }

            logger.info("root task", new RuntimeException("test"));
            log.info("root task", new RuntimeException("test"));
            return this.map(tasks, TWO_NAME);
        }

        if (context.isTask(TWO_NAME)) {
            MrTaskTest task = (MrTaskTest) context.getTask();

            System.out.println("two paramsId=" + task.getId());
            List<MrTaskTest> tasks = new ArrayList<>();
            for (int i = 1; i < SECOND_COUNT; i++) {
                tasks.add(new MrTaskTest(i, Lists.newArrayList(String.valueOf(task.getId() * i))));
            }

            return this.map(tasks, THREE_NAME);
        }

        if (context.isTask(THREE_NAME)) {
            MrTaskTest task = (MrTaskTest) context.getTask();
            System.out.println("three=" + task.getId() + " name=" + task.getNames().get(0));

            return new ProcessResult(true, String.valueOf(task.getId() * 2));
        }

        return new ProcessResult(false);
    }

    @Override
    public ProcessResult reduce(JobContext jobContext) {
        return new ProcessResult(true);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MrTaskTest {
        private Integer id;
        private List<String> names;
    }

    @Data
    @AllArgsConstructor
    public static class MyTestTask {
        private String name;
    }
}
