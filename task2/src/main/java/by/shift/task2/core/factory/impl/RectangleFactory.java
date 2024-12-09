package by.shift.task2.core.factory.impl;

import by.shift.task2.core.factory.FigureFactory;
import by.shift.task2.core.model.Figure;
import by.shift.task2.core.model.impl.Rectangle;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.service.impl.validation.RectangleValidationService;

public class RectangleFactory implements FigureFactory {
    @Override
    public Figure createFigure(FileData fileData) {
        boolean valid = new RectangleValidationService().isValidParameters(fileData);
        if (valid){
            return new Rectangle();
        }
        return null;
    }
}
