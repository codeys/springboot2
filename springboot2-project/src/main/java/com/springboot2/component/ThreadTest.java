package com.springboot2.component;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Administrator
 * @version 1.0
 * @title Thread
 * @description 线程池、completableFuture异步编排
 * @create 2024/12/12 13:55
 */
@Slf4j
public class ThreadTest {

    public static ExecutorService executors = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        /***************************线程创建与执行**********************/
        // 1. 继承Thread类
//        Thread thread01 = new Thread(new Thread01());
//        thread01.start();
        // 2. 实现Runnable接口
//        Thread runnable01 = new Thread(new Runnable01());
//        runnable01.start();
        // 3. 实现Callable接口，获取线程返回值
//        FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
//        Thread callable01 = new Thread(futureTask);
//        callable01.start();
//        log.info("Callable执行结果-{}",futureTask.get());
        // 4. 线程池
        // 4.1 无返回结果
//        Thread runnable02 = new Thread(new Runnable01());
//        executors.execute(runnable02);
        //4.2 获取返回结果
//        Future<Integer> submit = executors.submit(new Callable01());
//        log.info("Callable执行结果-{}",submit.get());

        /***************************CompletableFuture异步编排**********************/
        /**
         * 场景一
         * 利用CompletableFuture执行线程，无返回结果
         * runAsync
         */
//        CompletableFuture.runAsync(() -> {
//            log.info("当前线程-{}", Thread.currentThread().getId());
//            int result = 10 / 2;
//            log.info("任务{}-执行结果: {}", Thread.currentThread().getId(), result);
//        }, executors);
        /**
         * 场景二
         * 利用CompletableFuture执行线程，获取返回结果
         * supplyAsync
         */
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            log.info("当前线程-{}", Thread.currentThread().getId());
//            int result = 10 / 2;
//            log.info("任务{}-执行结果: {}", Thread.currentThread().getId(), result);
//            return result;
//        }, executors);
//        log.info("CompletableFuture的执行结果：{}", future.get());

        /**
         * 场景三
         * 利用CompletableFuture执行线程，完成后回调,无法修改结果
         * whenCompleteAsync
         */
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            log.info("当前线程-{}", Thread.currentThread().getId());
//            int result = 10 / 2;
//            log.info("任务{}-执行结果: {}", Thread.currentThread().getId(), result);
//            return result;
//        }, executors).whenCompleteAsync((res,e) ->{
//            log.info("线程执行完毕-结果：{},异常：{}", res, e);
//        });
//        log.info("CompletableFuture的执行结果：{}", future.get());

        /**
         * 场景四
         * 利用CompletableFuture执行线程，完成后回调,且修改结果
         * handleAsync
         */
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            log.info("当前线程-{}", Thread.currentThread().getId());
//            int result = 10 / 2;
//            log.info("任务{}-执行结果: {}", Thread.currentThread().getId(), result);
//            return result;
//        }, executors).handleAsync((res,e) ->{
//            if (e != null) {
//                return 0;
//            }
//            return res * 2;
//        });
//        log.info("CompletableFuture的执行结果：{}", future.get());

        /**
         * 场景五
         * 线程串行化,上一个线程执行完成，执行下一线程
         * thenRunAsync 无参无结果
         * thenAcceptAsync 有参无结果
         * thenApplyAsync 有参有结果
         */
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            log.info("当前线程-{}", Thread.currentThread().getId());
//            int result = 10 / 2;
//            log.info("任务1-执行结果: {}", result);
//            return result;
//        }, executors).thenApplyAsync((res) -> {
//            log.info("任务2执行，当前线程-{}，上一线程结果：{}", Thread.currentThread().getId(),res);
//            return "hello" + res;
//        },executors);
//        log.info("CompletableFuture的执行结果：{}", future.get());

        /**
         * 场景六
         * 线程任务组合,两个线程都执行完毕，将两个线程的结果传递
         * runAfterBoth 无参无结果
         * thenAcceptBothAsync 有参无结果
         * thenCombine 有参有结果
         */
//        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
//            log.info("当前线程-{}", Thread.currentThread().getId());
//            int result = 10 / 2;
//            log.info("任务1-执行结果: {}", result);
//            return result;
//        }, executors);
//        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
//            log.info("当前线程-{}", Thread.currentThread().getId());
//            log.info("任务2-执行结果: {}", 3);
//            return 3;
//        }, executors);
//        future1.thenAcceptBothAsync(future2, (i, j) -> {
//            log.info("两个线程执行完毕，执行结果：{}", (i + j));
//        }, executors);

        /**
         * 场景七
         * 线程任务组合,任一线程执行完毕,将优先执行完毕的结果传递
         * runAfterEither 有参无结果
         * acceptEither 有参无结果
         * applyToEither 有参有结果
         */
//        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return 1;
//        },executors);
//
//        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
//            return 2;
//        },executors);
//        CompletableFuture<Integer> futureResult = future1.applyToEither(future2, i -> i * 2);
//        log.info("执行结果-> {}",futureResult.get());

        /**
         * 场景八
         * 多线程任务组合，等待所有任务执行完毕
         * allOf
         */
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 1;
        },executors);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 2;
        },executors);
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            return 3;
        },executors);
        CompletableFuture<Void> futureResult = CompletableFuture.allOf(future1, future2, future3);
        futureResult.get();
        log.info("所有任务全部执行完毕");

        /**
         * 场景九
         * 多线程任务组合，任一任务执行完毕,执行下一步处理
         * anyOf
         */
//        CompletableFuture<Object> futureResult = CompletableFuture.anyOf(future1, future2, future3);
//        futureResult.get();
//        log.info("有一个任务完成");
    }

    public static class Thread01 extends Thread {
        @Override
        public void run() {
            log.info("Thread-任务1线程-{}", Thread.currentThread().getId());
        }
    }

    public static class Runnable01 implements Runnable {
        @Override
        public void run() {
            log.info("Runnable-任务1线程-{}", Thread.currentThread().getId());
        }
    }

    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log.info("Callable-任务1线程-{}", Thread.currentThread().getId());
            return 0;
        }
    }



}
