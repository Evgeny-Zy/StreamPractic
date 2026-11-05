package com.example;

import com.example.forkjoin.FactorialTask;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        FactorialTask factorialTask = new FactorialTask(10);
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        System.out.println(forkJoinPool.invoke(factorialTask));
    }
}