package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.model.annotation.Figure;
import by.shift.task2.core.model.Calculator;
import java.util.LinkedHashMap;
import java.util.Map;

@Figure
public class Circle implements Calculator {

    private boolean hasSingleParameter(FileData fileData) {

        String parameters = fileData.getParameters();
        if (parameters == null) {
            return false;
        }

        return parameters.trim().split(" ").length == 1;
    }

    @Override
    public Result calculate(FileData fileData) {
        if (!hasSingleParameter(fileData)){
            throw new RuntimeException("Circle should have only one parameter");
        }
        double radius = Integer.parseInt(fileData.getParameters());
        double perimeter = 2 * Math.PI * radius;
        double square = Math.PI * radius * radius;
        double diameter = radius + radius;
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Square", square + " sq. mm");
        map.put("Perimeter", perimeter + " mm");
        map.put("Diameter", diameter + " mm");
        map.put("Radius", radius + " mm");
        return new Result("Circle", map);
    }

    @Override
    public boolean isSuitable(String name) {
        return name.equals("Circle");
    }

    @Override
    public String toString() {
        return "Circle";
    }
}
