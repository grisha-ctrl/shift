package by.shift.task4.core.model.impl;

import by.shift.task4.core.model.Series;
import java.math.BigDecimal;
import java.math.MathContext;

public class ZetaFunctionSeries implements Series {
    @Override
    public BigDecimal compute(int k, MathContext mathContext) {
        return BigDecimal.ONE.divide(BigDecimal.valueOf(k).pow(2), mathContext);
    }
}
