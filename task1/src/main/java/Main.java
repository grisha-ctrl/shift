import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int tableSize = initializeTableSize();
        int maxValueCellSize = String.valueOf(tableSize * tableSize * 10).length();

        buildMultiplicationTable(maxValueCellSize, tableSize);
    }

    public static void buildMultiplicationTable(int maxValueCellSize, int tableSize) {
        buildHeader(maxValueCellSize, tableSize);

        String rowSeparator = buildRowSeparator(maxValueCellSize, tableSize);

        System.out.println(rowSeparator);
        for (int rowNumber = 1; rowNumber <= tableSize; rowNumber++) {
            System.out.printf("%" + (maxValueCellSize - 1) + "s", rowNumber + "|");
            for (int columnNumber = 1; columnNumber <= tableSize; columnNumber++) {
                if (columnNumber == tableSize) {
                    System.out.printf("%" + (maxValueCellSize - 1) + "s", (rowNumber * columnNumber));
                } else {
                    System.out.printf("%" + maxValueCellSize + "s", (rowNumber * columnNumber) + "|");
                }
            }
            System.out.println();
            System.out.println(rowSeparator);
        }
    }

    public static void buildHeader(int maxValueCellSize, int tableSize) {
        for (int columnNumber = 0; columnNumber <= tableSize; columnNumber++) {
            if (columnNumber != 0) {
                if (columnNumber == tableSize) {
                    System.out.printf("%" + (maxValueCellSize - 1) + "s", columnNumber);
                } else {
                    System.out.printf("%" + maxValueCellSize + "s", columnNumber + "|");
                }
            } else {
                System.out.printf("%" + (maxValueCellSize - 1) + "s", "|");
            }
        }
        System.out.println();
    }

    public static String buildRowSeparator(int maxValueCellSize, int tableSize) {
        StringBuilder builder = new StringBuilder();
        builder.append("-".repeat(maxValueCellSize - 2));
        builder.append("+");

        for (int i = 0; i < tableSize; i++) {
            if (i != tableSize - 1) {
                builder.append("-".repeat(maxValueCellSize - 1));
                builder.append("+");
            } else {
                builder.append("-".repeat(maxValueCellSize - 1));
            }
        }

        return builder.toString();
    }

    public static int initializeTableSize() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter table size: ");

        int tableSize = scanner.nextInt();

        if (tableSize > 32 || tableSize < 0) {
            throw new RuntimeException("Invalid multiplication table size");
        }

        return tableSize;
    }
}

