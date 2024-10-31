package by.shift.task2;

import by.shift.task2.core.model.Context;
import by.shift.task2.core.model.Result;
import by.shift.task2.core.service.Inspector;
import by.shift.task2.core.service.InspectorImpl;
import java.io.File;

public class Main {
    public static void main(String[] args){
        String path = args[0];
        File dir = new File("task2/src/main/java/by/shift/task2/core/model");
        Context.setUp(dir);
        Inspector inspector = new InspectorImpl();
        Result result =inspector.inspect(path);
        System.out.println(result);//текст блок
    }
}