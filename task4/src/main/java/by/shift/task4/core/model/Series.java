package by.shift.task4.core.model;

import java.math.BigDecimal;
import java.math.MathContext;

public interface Series {
    BigDecimal compute(int k, MathContext mathContext);
}