package com.training.thread.singleThread.exceptionHandler;

/**
 * @Author Wong Jwei
 * @Date 2021/7/1
 * @Description
 */
public class ThreadHandlerTest {

    public static void main(String[] args) throws InterruptedException {
        Thread.UncaughtExceptionHandler exceptionHandler = (t, e) -> {
            System.out.println("Thread:" + t.getName());
            System.out.println("Throwing exception:" + e);
        };
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("throw a new Exception ! ");
        });
        thread.setUncaughtExceptionHandler(exceptionHandler); // set exception handler
        thread.start();
        System.out.println("Wait for terminating the thread");
        thread.join();
        System.out.println("Thread terminated");
    }

}
