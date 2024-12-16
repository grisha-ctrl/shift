package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.Figure;
import by.shift.task2.core.model.FileData;
import lombok.Getter;

@Getter
public class Triangle implements Figure {

    String name = "Triangle";

    double sideA;
    double sideB;
    double sideC;

    double cornerA;
    double cornerB;
    double cornerC;

    double perimeter;
    double square;

    @Override
    public void calculate(FileData fileData) {

        String[] numberStrings = fileData.getParameters().split(" ");

        sideA = Integer.parseInt(numberStrings[0]);
        sideB = Integer.parseInt(numberStrings[1]);
        sideC = Integer.parseInt(numberStrings[2]);

        perimeter = sideA + sideB + sideC;
        double halfPerimeter = perimeter / 2;
        double sqrt = halfPerimeter * ((halfPerimeter - sideA) * (halfPerimeter - sideB) * (halfPerimeter - sideC));
        square = Math.sqrt(sqrt);

        cornerA = Math.toDegrees(Math.acos( ((sideB * sideB) + (sideC * sideC) - (sideA * sideA)) / (2 * sideB * sideC)));
        cornerB = Math.toDegrees(Math.acos( ((sideA * sideA) + (sideC * sideC) - (sideB * sideB)) / (2 * sideA * sideC)));
        cornerC = Math.toDegrees(Math.acos( ((sideB * sideB) + (sideA * sideA) - (sideC * sideC)) / (2 * sideB * sideA)));

    }


    @Override
    public String toString() {
        return "Figure: " + name + System.lineSeparator() +
                "Side A " + sideA + " mm " + "and the opposite angle: " + cornerA + " degrees" + System.lineSeparator() +
                "Side B " + sideB + " mm " + "and the opposite angle: " + cornerB + " degrees" + System.lineSeparator() +
                "Side C " + sideC + " mm " + "and the opposite angle: " + cornerC + " degrees" + System.lineSeparator() +
                "Square " + square + " sq. mm" + System.lineSeparator() +
                "Perimeter " + square + " sq. mm";
    }
}