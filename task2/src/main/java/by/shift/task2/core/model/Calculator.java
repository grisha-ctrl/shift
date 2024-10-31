package by.shift.task2.core.model;

/**
 *  Интерфейс Calculator созданный для математических операций, в которые включены поля передаваемые в файле
 */
public interface Calculator extends Suitable {
    /**
     * Метод...
     * @param fileData
     * @return
     */
    Result calculate(FileData fileData);
}
