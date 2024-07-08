package com.kirilov.interview.test.revolut;

import com.kirilov.interview.revolut.loadbalancer.LoadBalancerFactory;
import com.kirilov.interview.revolut.loadbalancer.LoadBalancerMode;
import com.kirilov.interview.revolut.loadbalancer.Task;
import com.kirilov.interview.revolut.loadbalancer.ThreadSafeLoadBalancer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

public class LoadBalancerTests {

    @BeforeEach
    public void setUp() {
        LoadBalancerFactory.INSTANCE.resetLoadBalancers();
    }

    @Test
    public void createRoundRobin() {
        //GIVEN empty factory
        //WHEN getting a loadBalancer of RoundRobin
        var roundRobin = LoadBalancerFactory.INSTANCE.getLoadBalancer(LoadBalancerMode.ROUND_ROBIN);

        //THEN create 1 is created
        Assertions.assertEquals(LoadBalancerMode.ROUND_ROBIN, roundRobin.getMode());
        Assertions.assertEquals(10, roundRobin.getNumberActiveWorkers());
        //AND validate 10 instances

        //WHEN call get again
        var roundRobin2 = LoadBalancerFactory.INSTANCE.getLoadBalancer(LoadBalancerMode.ROUND_ROBIN);
        //THEN the same instance is returned
        Assertions.assertSame(roundRobin, roundRobin2);
    }

    @Test
    public void createRandom() {
        //GIVEN empty factory
        //WHEN getting a loadBalancer of RANDOM
        var random = LoadBalancerFactory.INSTANCE.getLoadBalancer(LoadBalancerMode.RANDOM);

        //THEN create 1 is created
        Assertions.assertEquals(LoadBalancerMode.RANDOM, random.getMode());
        Assertions.assertEquals(10, random.getNumberActiveWorkers());
        //AND validate 10 instances

        //WHEN call get again
        var random2 = LoadBalancerFactory.INSTANCE.getLoadBalancer(LoadBalancerMode.RANDOM);
        //THEN the same instance is returned
        Assertions.assertSame(random, random2);
    }

    @Test
    public void executeTaskRoundRobin() {
        //GIVEN 1 roundrobin LB
        var roundRobin = LoadBalancerFactory.INSTANCE.getLoadBalancer(LoadBalancerMode.ROUND_ROBIN);

        //WHEN passed 1 task
        Task task = new Task(1);

        //THEN task is executed by 1st worker
        String result = roundRobin.execute(task);
        Assertions.assertEquals("ROUND_ROBIN instance 0 executed task 1", result);
    }

    @Test
    public void executeMultipleTasksRoundRobin() {
        //GIVEN 1 roundrobin LB
        var roundRobin = LoadBalancerFactory.INSTANCE.getLoadBalancer(LoadBalancerMode.ROUND_ROBIN);

        //WHEN passed 3 tasks
        //THEN all tasks executed successfully 1, 2, 3
        IntStream.range(0, 3).forEach(
                i -> {
                    Task task = new Task(i);
                    String result = roundRobin.execute(task);
                    Assertions.assertEquals("ROUND_ROBIN instance " + i + " executed task " + i, result);
                }
        );
    }

    @Test
    public void executeOneRoundOfTasksRoundRobin() {
        //GIVEN 1 roundrobin LB
        var roundRobin = LoadBalancerFactory.INSTANCE.getLoadBalancer(LoadBalancerMode.ROUND_ROBIN);

        //WHEN passed 11 tasks
        //THEN all tasks executed successfully
        //AND is 1st instance executed 2 tasks
        IntStream.range(0, 11).forEach(
                i -> {
                    Task task = new Task(i);
                    String result = roundRobin.execute(task);
                    Assertions.assertEquals("ROUND_ROBIN instance " + i % 10 + " executed task " + i, result);
                }
        );
    }

    //TODO negative - pass broken task, do some validation

    @Test
    public void executeOneRoundOfTasksRandom() {
        //GIVEN 1 roundrobin LB
        ThreadSafeLoadBalancer random = (ThreadSafeLoadBalancer) LoadBalancerFactory.INSTANCE.getLoadBalancer(LoadBalancerMode.RANDOM);

        //WHEN passed 100 tasks
        //THEN all tasks executed successfully
        //AND is 1st instance executed 2 tasks
        IntStream.range(0, 100).forEach(
                i -> {
                    Task task = new Task(i);
                    String result = random.execute(task);
                    Assertions.assertTrue(result.startsWith("RANDOM instance "));
                }
        );

        //if all worker's executed tasks are different, than they are truely random.
        List<Integer> uniqueExecutedTasksCount = random.getWorkerId2ExecutedTaskCount().values().stream().distinct().toList();
        Assertions.assertTrue(uniqueExecutedTasksCount.size() > 1);

        //Another way is to create a 2nd random and compare their execution plans
    }
}
