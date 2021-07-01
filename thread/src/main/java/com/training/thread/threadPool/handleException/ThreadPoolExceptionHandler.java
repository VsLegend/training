package com.training.thread.threadPool.handleException;

import java.util.concurrent.*;

/**
 * @Author Wong Jwei
 * @Date 2021/7/1
 * @Description thread pool exception handler
 */
public class ThreadPoolExceptionHandler extends ThreadPoolExecutor {

    public ThreadPoolExceptionHandler(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }


    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        // catch the exception
        System.out.println("Thread:" + r.toString());
        System.out.println("Throwing exception:" + t);
    }

    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExceptionHandler threadPoolExecutor = new ThreadPoolExceptionHandler(processors, processors,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        threadPoolExecutor.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("throw a new Exception ! ");
        });
        threadPoolExecutor.shutdown();
        System.out.println("Thread terminated");
    }

}
