package com.kirilov.interview.revolut.loadbalancer;

public class Task {
    public int id;

    public Task(int id) {
        this.id = id;
    }

    String execute() {
        return "task executed"; //dummy task
    }
}
