package by.shift.task2;

import by.shift.task2.core.factory.FigureFactory;
import by.shift.task2.core.factory.OutputServiceFactory;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.factory.impl.CircleFactory;
import by.shift.task2.core.factory.impl.RectangleFactory;
import by.shift.task2.core.factory.impl.TriangleFactory;
import by.shift.task2.core.service.OutputService;
import by.shift.task2.core.utils.*;

public class Main {
    public static void main(String[] args) {
        String path = args[0];
        String outputType = args[1];
        String fileName = args.length > 2 ? args[2] : null;
        FileData fileData = DataReaderUtils.read(path);
        String figureName = fileData.getType();
        FigureFactory factory = createFigureByFactory(figureName);
        var figure = factory.createFigure(fileData);
        figure.calculate(fileData);
        OutputService outputService = OutputServiceFactory.create(outputType, fileName);
        outputService.write(String.valueOf(figure));

    }

    private static FigureFactory createFigureByFactory(String figureName) {
        if (figureName == null) {
            throw new IllegalArgumentException("Figure name cannot be null");
        }
        return switch (figureName.toLowerCase()) {
            case "circle" -> new CircleFactory();
            case "rectangle" -> new RectangleFactory();
            case "triangle" -> new TriangleFactory();
            default -> throw new AssertionError(figureName + " document does not exist");
        };
    }

}