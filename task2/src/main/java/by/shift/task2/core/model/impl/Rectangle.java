package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.model.annotation.Figure;
import by.shift.task2.core.model.Calculator;

@Figure
public class Rectangle implements Calculator {

    @Override
    public Result calculate(FileData fileData) {
        String[] numberStrings = fileData.getParameters().split(" ");

        int sideA = Integer.parseInt(numberStrings[0]);
        int sideB = Integer.parseInt(numberStrings[1]);

        double perimeter = (sideA * 2) + (sideB * 2);
        double square = sideA * sideB;
        double diagonal = Math.sqrt(sideA*sideA+sideB*sideB);

        String builder = "Тип фигуры: Прямоугольник" + System.getProperty("line.separator") +
                "Площадь: " + square + System.getProperty("line.separator") +
                "Периметр: " + perimeter + System.getProperty("line.separator") +
                "Ширина: " + sideA + System.getProperty("line.separator") +
                "Длина: " + sideB + System.getProperty("line.separator") +
                "Длина диагонали: " + diagonal;

        System.out.println(builder);
        return null;
    }

    @Override
    public boolean isSuitable(String name) {
        return name.equals("Rectangle");
    }
}
