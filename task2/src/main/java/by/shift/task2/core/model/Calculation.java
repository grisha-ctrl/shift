package by.shift.task2.core.model;

public interface Calculation {//переименовать шоб понять зачем он и что он делает
    void calculate(FileData fileData);

    boolean isSuitable(String a);
}
