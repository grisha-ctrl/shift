package by.shift.task2.core.service.impl;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.service.FigureValidationService;

public class RectangleValidationService implements FigureValidationService {

    @Override
    public boolean isValidParameters(FileData fileData) {
        String[] parametersArray = getStrings(fileData);

        try {
            int sideA = Integer.parseInt(parametersArray[0]);
            int sideB = Integer.parseInt(parametersArray[1]);

            if (sideA <= 0 || sideB <= 0 ) {
                throw new RuntimeException("All sides of the rectangle must be positive integers.");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Parameters must be valid integers. Check the file for correctness of data.", e);
        }

        return true;
    }

    private static String[] getStrings(FileData fileData) {
        String parameters = fileData.getParameters();
        if (parameters == null) {
            throw new RuntimeException("Figure name or parameters are missing. Check the file for correctness of data.");
        }
        String[] parametersArray = fileData.getParameters().trim().split(" ");

        if (parametersArray.length != 2) {
            throw new RuntimeException("Rectangle should have exactly two parameters. Check the file for correctness of data.");
        }
        return parametersArray;
    }
}
