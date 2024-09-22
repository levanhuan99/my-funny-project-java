package it.must.be.funny.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiCallSimulation {
    public static void main1(String[] args) {
        int numberOfCalls = 10000;
        int threadPoolSize = 10; // Số lượng thread giới hạn trong pool

        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        for (int i = 0; i < numberOfCalls; i++) {
            String caller = "090" + String.format("%07d", i);
            String callee = "091" + String.format("%07d", i);

            CallSimulation callSimulation = new CallSimulation(caller, callee);
            executor.submit(callSimulation);
        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(Math.random() * 3000);

        }
    }
}
