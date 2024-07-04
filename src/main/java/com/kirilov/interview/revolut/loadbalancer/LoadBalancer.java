package com.kirilov.interview.revolut.loadbalancer;

public interface LoadBalancer {
    LoadBalancerMode getMode();
    String execute(Task task);
    int getNumberActiveWorkers();
}
