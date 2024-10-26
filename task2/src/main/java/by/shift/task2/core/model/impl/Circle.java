package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.annotation.Figure;
import by.shift.task2.core.model.Calculator;

@Figure
public class Circle implements Calculator {

    @Override
    public void calculate(FileData fileData ) {

        double r = Integer.parseInt(fileData.getParameters());

        double perimeter = 2 * Math.PI * r;

        double square = Math.PI * r * r;

        double diameter = r + r;

        System.out.println("четатам " + diameter + " " + fileData.getType() + " " + r + " " + perimeter + " " + square);
    }

    @Override
    public boolean isSuitable(String name) {

        return name.equals("Circle");
    }
}
