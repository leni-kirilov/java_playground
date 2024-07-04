package com.kirilov.interview.revolut.loadbalancer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadSafeLoadBalancer implements LoadBalancer {

    private AtomicInteger lastWorkerId = new AtomicInteger(0);

    private final List<Worker> workers;
    public final LoadBalancerMode mode;
    private final Map<Integer, Integer> workerId2ExecutedTaskCount;

    ThreadSafeLoadBalancer(int capacity, LoadBalancerMode mode) {
        this.mode = mode;
        workers = Collections.synchronizedList(new ArrayList<>(capacity)); //for future modifications
        workerId2ExecutedTaskCount = new ConcurrentHashMap<>(); //stats might be off otherwise

        IntStream.range(0, capacity).forEach(i -> {
                    workers.add(new Worker(i));
                    workerId2ExecutedTaskCount.put(i, 0);
                }
        );
    }

    @Override
    public LoadBalancerMode getMode() {
        return mode;
    }

    @Override
    public int getNumberActiveWorkers() {
        return workers.size();
    }

    @Override
    public String execute(Task task) {
        int workerId;

        workerId = switch (mode) {
            case RANDOM:
                yield ThreadLocalRandom.current().nextInt(workers.size()); //thread-safe and optimized Randomizer

            case ROUND_ROBIN:
                yield lastWorkerId.getAndUpdate(id -> (id + 1) % workers.size());
        };

        Worker worker = workers.get(workerId);
        if (mode == LoadBalancerMode.RANDOM) { //optimization as ROUND_ROBIN should be fair
            workerId2ExecutedTaskCount.compute(workerId, (key, currentValue) -> ++currentValue);
        }

        return mode.name() + " " + worker.execute(task);
    }

    public Map<Integer, Integer> getWorkerId2ExecutedTaskCount() {
        return Map.copyOf(workerId2ExecutedTaskCount);
    }
}
