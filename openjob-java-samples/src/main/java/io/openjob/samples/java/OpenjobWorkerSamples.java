package io.openjob.samples.java;

import io.openjob.worker.OpenjobWorker;
import io.openjob.samples.java.processor.DelayProcessorSample;
import io.openjob.samples.java.processor.JavaProcessorSample;
import io.openjob.samples.java.processor.MapReduceProcessorSample;

/**
 * @author stelin swoft@qq.com
 * @see JavaProcessorSample
 * @see DelayProcessorSample
 * @see MapReduceProcessorSample
 * @since 1.0.0
 */
public class OpenjobWorkerSamples {

    /**
     * Main
     *
     * @param args args
     */
    public static void main(String[] args) {
        try {
            OpenjobWorker openjobWorker = new OpenjobWorker();
            openjobWorker.init();

            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
