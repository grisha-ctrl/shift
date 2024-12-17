package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.Figure;
import by.shift.task2.core.model.FileData;
import lombok.Getter;

@Getter
public class Rectangle implements Figure {

    String name = "Rectangle";

    double perimeter;
    double square;
    double diagonal;

    double sideB;
    double sideA;

    @Override
    public void calculate(FileData fileData) {

        String[] numberStrings = fileData.getParameters().split(" ");

        sideA = Integer.parseInt(numberStrings[0]);
        sideB = Integer.parseInt(numberStrings[1]);

        perimeter = (sideA * 2) + (sideB * 2);
        square = sideA * sideB;
        diagonal = Math.sqrt(sideA * sideA + sideB * sideB);
    }


}
