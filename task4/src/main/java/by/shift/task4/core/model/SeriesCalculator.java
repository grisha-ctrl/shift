package by.shift.task4.core.model;

import by.shift.task4.task.Task;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Slf4j
public class SeriesCalculator {

    public BigDecimal calculate(int N, Series function) {
        try {
            int numThreads = 4;
            log.info("спользуется {} потоков.", numThreads);

            ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            List<Future<BigDecimal>> results = new ArrayList<>();

            int chunkSize = N / numThreads;
            for (int i = 0; i < numThreads; i++) {
                int start = i * chunkSize + 1;
                int end = (i == numThreads - 1) ? N : (start + chunkSize - 1);
                Task task = new Task(start, end, function);
                results.add(executor.submit(task));
                log.info("Создана задача для диапазона: {} - {}", start, end);
            }

            BigDecimal totalSum = BigDecimal.ZERO;
            for (Future<BigDecimal> result : results) {
                totalSum = totalSum.add(result.get());
            }

            executor.shutdown();
            log.info("Вычисления завершены. тоговая сумма: {}", totalSum);
            return totalSum;
        } catch (Exception e) {
            log.error("Ошибка выполнения вычислений", e);
            throw new RuntimeException("Ошибка выполнения вычислений", e);
        }
    }
}

