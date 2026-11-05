package com.example.forkjoin;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {
    private Integer start = 1;
    private Integer end;
    private final Integer fixedNumber = 4;

    public FactorialTask(Integer end) {
        this.end = end;
    }

    private FactorialTask(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int result = 1;
        if ((end - start) <= fixedNumber) {
            for (int i = start; i <= end; i++) {
                result *= i;
            }
            return result;
        }

        int mid = (start + end) / 2;

        FactorialTask left = new FactorialTask(start, mid);
        FactorialTask right = new FactorialTask(mid + 1, end);

        left.fork();

        Integer resRight = right.compute();
        Integer resLeft = left.join();

        return resRight * resLeft;
    }
}
