package by.shift.task4.core.model.impl;

import by.shift.task4.core.model.Series;
import java.math.BigDecimal;
import java.math.MathContext;

public class GeometricSeries implements Series {
    @Override
    public BigDecimal compute(int k, MathContext mathContext) {
        BigDecimal q = new BigDecimal("0.5");
        BigDecimal r = q.multiply(BigDecimal.valueOf(k));
        return r.pow(k , mathContext);
    }
}