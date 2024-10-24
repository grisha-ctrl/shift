package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.annotation.Figure;
import by.shift.task2.core.model.Calculation;

@Figure
public class Rectangle implements Calculation {

    @Override
    public void calculate(FileData fileData) {

        String[] numberStrings = fileData.getParameters().split(" ");
        int sideA = Integer.parseInt(numberStrings[0]);
        int sideB = Integer.parseInt(numberStrings[1]);

        double perimeter = (sideA * 2) + (sideB * 2);

        double square = sideA * sideB;


        System.out.println("четатам " + " " + fileData.getType() + " " + perimeter + " " + square);
    }

    @Override
    public boolean isSuitable(String name) {

        return name.equals("Rectangle");
    }
}
