package by.shift.task2.core.service.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.service.FigureValidationService;

public class TriangleValidationService implements FigureValidationService {

    @Override
    public boolean isValidParameters(FileData fileData) {
        String[] parameters = fileData.getParameters().trim().split(" ");

        if (parameters.length != 3) {
            throw new RuntimeException("Triangle should have exactly three parameters. Check the file for correctness of data.");
        }
        try {
            int sideA = Integer.parseInt(parameters[0]);
            int sideB = Integer.parseInt(parameters[1]);
            int sideC = Integer.parseInt(parameters[2]);

            if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
                throw new RuntimeException("All sides of the triangle must be positive integers.");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Parameters must be valid integers. Check the file for correctness of data.", e);
        }

        return true;
    }
}
