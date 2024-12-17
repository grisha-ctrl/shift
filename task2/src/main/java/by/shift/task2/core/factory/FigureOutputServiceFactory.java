package by.shift.task2.core.factory;

import by.shift.task2.core.service.FigureOutputService;
import by.shift.task2.core.service.impl.output.CircleOutputService;
import by.shift.task2.core.service.impl.output.RectangleOutputService;
import by.shift.task2.core.service.impl.output.TriangleOutputService;

import java.util.HashMap;
import java.util.Map;

public class FigureOutputServiceFactory {
    private static final Map<String, FigureOutputService> serviceRegistry = new HashMap<>();

    static {
        serviceRegistry.put("circle", new CircleOutputService());
        serviceRegistry.put("rectangle", new RectangleOutputService());
        serviceRegistry.put("triangle", new TriangleOutputService());
    }

    public static FigureOutputService getService(String figureName) {
        FigureOutputService service = serviceRegistry.get(figureName.toLowerCase());
        if (service == null) {
            throw new IllegalArgumentException("No service found for figure: " + figureName);
        }
        return service;
    }
}
