package by.shift.task4.core.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class InputHandler {

    public static int getN() {
        Scanner scanner = new Scanner(System.in);
        log.info("Запрашиваем значение N у пользователя.");
        System.out.println("Введите значение N: ");
        int N = scanner.nextInt();
        log.info("Пользователь ввёл N = {}", N);
        return N;
    }
}

