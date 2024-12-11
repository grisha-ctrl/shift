package by.shift.task4.core.util;

import by.shift.task4.core.model.factory.SeriesFactory;
import by.shift.task4.core.model.factory.impl.GeometricSeriesFactory;
import by.shift.task4.core.model.factory.impl.InverseSquareSeriesFactory;
import by.shift.task4.core.model.factory.impl.TelescopingSeriesFactory;
import by.shift.task4.core.model.factory.impl.ZetaFunctionSeriesFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class FunctionSelector {

    private FunctionSelector(){}

    public static SeriesFactory chooseFunction() {
        Scanner scanner = new Scanner(System.in);
        log.info("Запрашиваем выбор функции у пользователя.");
        System.out.println("Выберите функцию для вычислений:");
        System.out.println("1 - Телескопический ряд");
        System.out.println("2 - Функция дзета Римана");
        System.out.println("3 - нверсивный квадрат");
        System.out.println("4 - Геометрическая функция");

        int functionChoice = scanner.nextInt();
        log.info("Пользователь выбрал функцию №{}", functionChoice);

        return switch (functionChoice) {
            case 1 -> new TelescopingSeriesFactory();
            case 2 -> new ZetaFunctionSeriesFactory();
            case 3 -> new InverseSquareSeriesFactory();
            case 4 -> new GeometricSeriesFactory();
            default -> {
                log.error("Некорректный выбор функции: {}", functionChoice);
                throw new IllegalArgumentException("Некорректный выбор функции: " + functionChoice);
            }
        };
    }
}

