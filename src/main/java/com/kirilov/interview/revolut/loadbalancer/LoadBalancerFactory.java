package com.kirilov.interview.revolut.loadbalancer;

// * 1. Implement thread-safe load-balancer with max of 10 instances and different load-balancing strategies support(round robin and random)

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum LoadBalancerFactory { //thread-safe and lazy-initialized
    INSTANCE;

    private final int DEFAULT_CAPACITY = 10;
    private final Map<LoadBalancerMode, LoadBalancer> loadBalancerInstances = new ConcurrentHashMap<>();

    public LoadBalancer getLoadBalancer(LoadBalancerMode mode) {
        return loadBalancerInstances.computeIfAbsent(
                mode,
                m -> new ThreadSafeLoadBalancer(DEFAULT_CAPACITY, m)
        );
    }

    public void resetLoadBalancers() {
        loadBalancerInstances.clear();
    }
}

class Worker {
    int id;

    Worker(int id) {
        this.id = id;
    }

    //doesn't really execute
    synchronized String execute(Task task) {
//        String result = task.execute(); //. Result = %s
        return String.format("instance %d executed task %d", id, task.id);
    }
}