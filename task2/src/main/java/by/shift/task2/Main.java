package by.shift.task2;

import by.shift.task2.core.model.Context;
import by.shift.task2.core.service.Service;

import java.io.File;

public class Main {
    public static void main(String[] args){
        File dir = new File("C:\\Users\\GRISHKA_UBICA_RUS76\\IdeaProjects\\shift\\task2\\src\\main\\java\\by\\shift\\task2\\core\\model");
        Context.setUp(dir);
        Service service = new Service();
        service.service();
    }
}