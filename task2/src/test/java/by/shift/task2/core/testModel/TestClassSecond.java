package by.shift.task2.core.testModel;

import by.shift.task2.core.model.Calculator;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.model.annotation.Figure;

@Figure
public class TestClassSecond implements Calculator {
    @Override
    public Result calculate(FileData fileData) {
        return null;
    }

    @Override
    public boolean isSuitable(String a) {
        return false;
    }
}
