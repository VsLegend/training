package com.training.thread.singleThread.exceptionHandler;

import java.util.concurrent.*;

/**
 * @Author Wong Jwei
 * @Date 2021/7/1
 * @Description
 */
public class CallableExceptionHandleTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Boolean> callable = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("throw a new Exception ! ");
            //  return true;
        };
        Future<Boolean> submit = executorService.submit(callable);
        if (!submit.isDone()) { //
            try {
                Boolean aBoolean = submit.get();
                System.out.println(aBoolean);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Throwing exception:" + e); // handler the exception from callable
            }
        }
        executorService.shutdown();
    }

}
