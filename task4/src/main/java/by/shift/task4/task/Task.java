package by.shift.task4.task;

import by.shift.task4.core.model.Series;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.Callable;

public class Task implements Callable<BigDecimal> {
    private final int start;
    private final int end;
    private final Series function;
    private final MathContext mathContext = new MathContext(20); // Точность вычислений

    public Task(int start, int end, Series function) {
        this.start = start;
        this.end = end;
        this.function = function;
    }

    @Override
    public BigDecimal call() {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = start; i <= end; i++) {
            sum = sum.add(function.compute(i, mathContext), mathContext);
        }
        return sum;
    }
}


