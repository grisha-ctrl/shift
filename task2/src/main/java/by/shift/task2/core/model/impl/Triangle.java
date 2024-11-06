package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.model.annotation.Figure;
import by.shift.task2.core.model.Calculator;

import java.util.LinkedHashMap;
import java.util.Map;

@Figure
public class Triangle implements Calculator {
    private boolean hasThreeParameters(FileData fileData) {

        String parameters = fileData.getParameters();
        if (parameters == null) {
            return false;
        }

        return parameters.trim().split(" ").length == 3;
    }
    @Override
    public Result calculate(FileData fileData) {
        if (!hasThreeParameters(fileData)){
            throw new RuntimeException("Circle should have only three parameters");
        }
        String[] numberStrings = fileData.getParameters().split(" ");

        int sideA = Integer.parseInt(numberStrings[0]);
        int sideB = Integer.parseInt(numberStrings[1]);
        int sideC = Integer.parseInt(numberStrings[2]);

        double perimeter = sideA + sideB + sideC;
        double halfPerimeter = perimeter / 2;
        double sqrt = halfPerimeter * ((halfPerimeter - sideA) * (halfPerimeter - sideB) * (halfPerimeter - sideC));
        double square = Math.sqrt(sqrt);


        double cornerA = Math.toDegrees(Math.acos((double) ((sideB * sideB) + (sideC * sideC) - (sideA * sideA)) / (2 * sideB * sideC)));
        double cornerB = Math.toDegrees(Math.acos((double) ((sideA * sideA) + (sideC * sideC) - (sideB * sideB)) / (2 * sideA * sideC)));
        double cornerC = Math.toDegrees(Math.acos((double) ((sideB * sideB) + (sideA * sideA) - (sideC * sideC)) / (2 * sideB * sideA)));

        Map<String, String> map = new LinkedHashMap<>();
        map.put("Square", square + " sq. mm");
        map.put("Perimeter", perimeter + " mm");
        map.put("Side A", sideA + " mm " + "and the opposite angle: " + cornerA + " degrees");
        map.put("Side B", sideB + " mm " + "and the opposite angle: " + cornerB + " degrees");
        map.put("Side C", sideC + " mm " + "and the opposite angle: " + cornerC + " degrees");
        return new Result("Triangle", map);
    }

    @Override
    public boolean isSuitable(String name) {
        return name.equals("Triangle");
    }

    @Override
    public String toString() {
        return "Triangle";
    }
}