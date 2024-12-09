package by.shift.task2.core.service.impl.validation;

import by.shift.task2.core.model.FileData;
import by.shift.task2.core.service.FigureValidationService;

public class CircleValidationService implements FigureValidationService {

    @Override
    public boolean isValidParameters(FileData fileData) {
        String[] parametersArray = getStrings(fileData);

        try {
            int firstParameter = Integer.parseInt(parametersArray[0]);

            if (firstParameter <= 0) {
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

        if (parametersArray.length != 1) {
            throw new RuntimeException("Circle should have exactly one parameters. Check the file for correctness of data.");
        }
        return parametersArray;
    }
}
