package by.shift.task4;

import by.shift.task4.core.model.SeriesCalculator;
import by.shift.task4.core.model.factory.SeriesFactory;
import by.shift.task4.core.util.FunctionSelector;
import by.shift.task4.core.util.InputHandler;
import by.shift.task4.core.util.ResultHandler;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;

@Slf4j
public class MultithreadedSeriesCalculator {

    public static void main(String[] args) {

        int N = InputHandler.getN();
        SeriesCalculator seriesCalculator = new SeriesCalculator();
        SeriesFactory function = FunctionSelector.chooseFunction();
        var series = function.createSeries();
        BigDecimal result = seriesCalculator.calculate(N, series);

        ResultHandler.displayResult(result);
        log.info("Программа завершена успешно.");
    }
}



