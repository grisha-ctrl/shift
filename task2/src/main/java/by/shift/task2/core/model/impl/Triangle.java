package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.annotation.Figure;
import by.shift.task2.core.model.Calculator;

@Figure
public class Triangle implements Calculator {


    public void calculate(FileData fileData) {

        String[] numberStrings = fileData.getParameters().split(" ");
        int sideA = Integer.parseInt(numberStrings[0]);
        int sideB = Integer.parseInt(numberStrings[1]);
        int sideC = Integer.parseInt(numberStrings[2]);

        double perimeter = sideA + sideB + sideC;
        double p = perimeter / 2;
        double sqrt = p * (p - sideA) * (p - sideB) * (p - sideC);
        double square = Math.sqrt(sqrt);
        System.out.println("четатам " + " " + fileData.getType() + " " + perimeter + " " + square);
    }

    @Override
    public boolean isSuitable(String name) {

        return name.equals("Triangle");
    }
}