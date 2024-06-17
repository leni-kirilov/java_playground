package com.kirilov.v19;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Virtual Threads vs OS Threads
 *
 */
public class VirtualThreads {

    public static final int NUMBER_OF_TASKS = 10_000;

    public static void main(String[] args) {
        runVirtualThreads();
//        runThreads(); //requires 8 miuntes
    }

    /**
     * finishes within 1 second
     */
    private static void runVirtualThreads() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, NUMBER_OF_TASKS).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println(i);
                    return i;
                });
            });
        }
    }

    /**
     * Slow. Depends on OS level threads. Could take >8 minutes if ran on 10cores (20 threads)
     */
    private static void runThreads() {
        try (var executor = Executors.newFixedThreadPool(20)) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println(i);
                    return i;
                });
            });
        }
    }
}
