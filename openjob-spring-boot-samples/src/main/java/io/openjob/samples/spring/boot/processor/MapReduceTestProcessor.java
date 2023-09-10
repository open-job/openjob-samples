package io.openjob.samples.spring.boot.processor;

import io.openjob.worker.context.JobContext;
import io.openjob.worker.processor.MapReduceProcessor;
import io.openjob.worker.processor.ProcessResult;
import io.openjob.worker.processor.TaskResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.7
 */
@Component("mapReduceTestProcessor")
public class MapReduceTestProcessor implements MapReduceProcessor {
    private static final Logger logger = LoggerFactory.getLogger("openjob");

    private static final String TWO_NAME = "TASK_TWO";

    private static final String THREE_NAME = "TASK_THREE";

    @Override
    public ProcessResult process(JobContext context) {
        if (context.isRoot()) {
            List<MapChildTaskTest> tasks = new ArrayList<>();
            for (int i = 1; i < 5; i++) {
                tasks.add(new MapChildTaskTest(i));
            }

            logger.info("Map Reduce root task mapList={}", tasks);
            return this.map(tasks, TWO_NAME);
        }

        if (context.isTask(TWO_NAME)) {
            MapChildTaskTest task = (MapChildTaskTest) context.getTask();
            List<MapChildTaskTest> tasks = new ArrayList<>();
            for (int i = 1; i < task.getId()*2; i++) {
                tasks.add(new MapChildTaskTest(i));
            }

            logger.info("Map Reduce task two mapList={}", tasks);
            return this.map(tasks, THREE_NAME);
        }

        if (context.isTask(THREE_NAME)) {
            MapChildTaskTest task = (MapChildTaskTest) context.getTask();
            logger.info("Map Reduce task three mapTask={}", task);
            return new ProcessResult(true, String.valueOf(task.getId() * 2));
        }

        return ProcessResult.success();
    }

    @Override
    public ProcessResult reduce(JobContext jobContext) {
        List<String> resultList = jobContext.getTaskResultList().stream().map(TaskResult::getResult)
                .collect(Collectors.toList());
        logger.info("Map Reduce resultList={}", resultList);
        return ProcessResult.success();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MapChildTaskTest {
        private Integer id;
    }
}
