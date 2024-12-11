package by.shift.task4.core.model.factory.impl;

import by.shift.task4.core.model.Series;
import by.shift.task4.core.model.factory.SeriesFactory;
import by.shift.task4.core.model.impl.InverseSquareSeries;

public class InverseSquareSeriesFactory implements SeriesFactory {
    @Override
    public Series createSeries() {
        return new InverseSquareSeries();
    }
}
