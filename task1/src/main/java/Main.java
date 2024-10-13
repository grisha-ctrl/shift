import java.util.Scanner;

public class Main {

    public static void buildHeader(int maxValueCellSize, int tableSize){

        for (int columnNumber = 0; columnNumber <= tableSize; columnNumber++) {
            if (columnNumber != 0) {
                if (columnNumber == tableSize) {
                    //переменные
                    System.out.printf("%" + (maxValueCellSize-1)+ "s", columnNumber);
                } else {
                    System.out.printf("%" + maxValueCellSize + "s", columnNumber + "|");
                }
            } else {
                System.out.printf("%" + (maxValueCellSize-1) + "s", "|");
            }
        }


    }

    public static int initializeTableSize(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter table size: ");

        int tableSize = scanner.nextInt();

        if (tableSize > 32 || tableSize < 0) {
            throw new RuntimeException("Invalid multiplication table size");
        }

        return tableSize;
    }

    public static void main(String[] args) {

        int tableSize = initializeTableSize();

        int maxValueCellSize = String.valueOf(tableSize * tableSize * 10).length();



        //header

        buildHeader(maxValueCellSize,tableSize);

        System.out.println();

        String rowSeparator = buildRowSeparator(maxValueCellSize, tableSize);

        System.out.println(rowSeparator);
        //header
        for (int i = 1; i <= tableSize; i++) {
            System.out.printf("%" + (maxValueCellSize - 1) + "s", i + "|");
            for (int j = 1; j <= tableSize; j++) {
                if (j == tableSize) {
                    System.out.printf("%" + (maxValueCellSize - 1) + "s", (i * j));
                } else {
                    System.out.printf("%" + maxValueCellSize + "s", (i * j) + "|");
                }
            }
            System.out.println();
            System.out.println(rowSeparator);
        }
    }
    //метод в мэйне только вызывается
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

}
