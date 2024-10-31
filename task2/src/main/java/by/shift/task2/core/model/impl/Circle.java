package by.shift.task2.core.model.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.model.annotation.Figure;
import by.shift.task2.core.model.Calculator;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Figure
public class Circle implements Calculator {

    private boolean hasSingleParameter(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();

            String parametersLine = reader.readLine();
            if (parametersLine == null) {
                return false;
            }

            String[] parameters = parametersLine.trim().split("\\s+");

            return parameters.length == 1;
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    // приватный метод который проверяет fileData на количество параметров в строке parameters
    @Override
    public Result calculate(FileData fileData) {

        double radius = Integer.parseInt(fileData.getParameters());
        double perimeter = 2 * Math.PI * radius;
        double square = Math.PI * radius * radius;
        double diameter = radius + radius;
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Площадь", square + " кв. мм");
        map.put("Периметр", perimeter + " мм");
        map.put("Диаметр", diameter + " мм");
        map.put("Радиус", radius + " мм");
        return new Result("Круг", map);
    }

    @Override
    public boolean isSuitable(String name) {
        return name.equals("Circle");
    }
}
