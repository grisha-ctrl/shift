package by.shift.task2;

import by.shift.task2.core.factory.FigureFactory;
import by.shift.task2.core.model.FileData;
import by.shift.task2.core.factory.impl.CircleFactory;
import by.shift.task2.core.factory.impl.RectangleFactory;
import by.shift.task2.core.factory.impl.TriangleFactory;
import by.shift.task2.core.service.FigureOutputService;
import by.shift.task2.core.factory.FigureOutputServiceFactory;
import by.shift.task2.core.utils.*;
import java.io.FileWriter;

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
        FigureOutputService figureOutputService = FigureOutputServiceFactory.getService(figureName);
        String figureData = figureOutputService.output(figure);
        OutputDestinationFactory.outputS(outputType, figureData, fileName);
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

    public static class OutputDestinationFactory {

        public static void outputS(String destination, String data, String filePath) {
            switch (destination.toLowerCase()) {
                case "console" -> System.out.println(data); // Вывод в консоль
                case "file" -> {
                    try (FileWriter writer = new FileWriter(filePath)) {
                        writer.write(data); // Запись в файл
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage(), e.getCause());
                    }
                }
            }
        }
    }
}