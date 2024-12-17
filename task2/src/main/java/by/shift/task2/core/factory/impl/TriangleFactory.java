package by.shift.task2.core.factory.impl;

import by.shift.task2.core.factory.FigureFactory;
import by.shift.task2.core.model.Figure;
import by.shift.task2.core.model.impl.Triangle;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.service.impl.validation.TriangleValidationService;

public class TriangleFactory implements FigureFactory {

    @Override
    public Figure createFigure(FileData fileData) {
        boolean valid = new TriangleValidationService().isValidParameters(fileData);
        if (valid){
            return new Triangle();
        }
        return null;
    }
}
