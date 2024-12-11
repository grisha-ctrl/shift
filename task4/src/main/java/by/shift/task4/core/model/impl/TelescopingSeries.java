package by.shift.task4.core.model.impl;

import by.shift.task4.core.model.Series;
import java.math.BigDecimal;
import java.math.MathContext;

public class TelescopingSeries implements Series {
    @Override
    public BigDecimal compute(int k, MathContext mathContext) {
        return BigDecimal.ONE.divide(BigDecimal.valueOf(k), mathContext)
                .subtract(BigDecimal.ONE.divide(BigDecimal.valueOf(k + 1), mathContext), mathContext);
    }
}