package by.shift.task1;

import java.text.MessageFormat;
import java.util.Scanner;

public class Main {
    private static final int MAX_TABLE_SIZE = 32;
    private static final int MIN_TABLE_SIZE = 1;
    private static final String INDENTION = "%{0}d";
    private static final String INNER_INDENTION = "|%{0}d";
    private static int firstColumnIndentionSize = 0;

    public static void main(String[] args) {
        int tableSize = initializeTableSize();
        int maxValueCellSize = String.valueOf(tableSize * tableSize).length();
        firstColumnIndentionSize = maxValueCellSize - 1;
        createMultiplicationTable(maxValueCellSize, tableSize);
    }

    private static int initializeTableSize() {
        try (Scanner scanner = new Scanner(System.in)) {
            int tableSize = -1;
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Enter table size: ");

                if (scanner.hasNextInt()) {
                    tableSize = scanner.nextInt();
                    if (tableSize > MAX_TABLE_SIZE || tableSize < MIN_TABLE_SIZE) {
                        System.out.println("Invalid multiplication table size, enter a number between 1 and 32.");
                    } else {
                        validInput = true;
                    }
                } else {
                    System.out.println("Invalid input, enter only numbers.");
                    scanner.next();
                }
            }
            return tableSize;
        }
    }

    private static void createMultiplicationTable(int maxValueCellSize, int tableSize) {
        int adjustedWidth = Math.max(1, maxValueCellSize - 1);
        String firstColumnSeparator = buildFirstColumn();
        String rowSeparator = firstColumnSeparator + buildRowSeparator(maxValueCellSize, tableSize);

        createHeader(maxValueCellSize, tableSize);

        System.out.println(rowSeparator);
        for (int rowNumber = 1; rowNumber <= tableSize; rowNumber++) {
            System.out.printf(MessageFormat.format(INDENTION, adjustedWidth), rowNumber);
            for (int columnNumber = 1; columnNumber <= tableSize; columnNumber++) {
                System.out.printf(MessageFormat.format(INNER_INDENTION, maxValueCellSize), (rowNumber * columnNumber));
            }
            System.out.println();
            System.out.println(rowSeparator);
        }
    }

    private static String buildFirstColumn() {
        if (firstColumnIndentionSize > 0){
            return "-".repeat(firstColumnIndentionSize) + "+";
        }else {
            return "-+";
        }
    }

    private static String buildRowSeparator(int maxValueCellSize, int tableSize) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < tableSize; i++) {
            if (i != tableSize - 1) {
                builder.append("-".repeat(maxValueCellSize));
                builder.append("+");
            } else {
                builder.append("-".repeat(maxValueCellSize));
            }
        }
        return builder.toString();
    }

    private static void createHeader(int maxValueCellSize, int tableSize) {
        int adjustedWidth = Math.max(1, maxValueCellSize - 1);
        System.out.printf("%" + adjustedWidth + "s", " ");
        for (int columnNumber = 1; columnNumber <= tableSize; columnNumber++) {
            System.out.printf(MessageFormat.format(INNER_INDENTION, maxValueCellSize), columnNumber);
        }
        System.out.println();
    }
}