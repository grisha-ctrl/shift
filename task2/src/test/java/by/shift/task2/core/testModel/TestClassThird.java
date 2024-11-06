package by.shift.task2.core.testModel;

import by.shift.task2.core.model.Calculator;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;

public class TestClassThird implements Calculator {
    @Override
    public Result calculate(FileData fileData) {
        return null;
    }

    @Override
    public boolean isSuitable(String a) {
        return false;
    }
}
