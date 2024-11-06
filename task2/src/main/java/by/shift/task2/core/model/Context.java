package by.shift.task2.core.model;

import by.shift.task2.core.model.annotation.Figure;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Context {
    private static final List<Calculator> FIGURES = new ArrayList<>();

    public static List<Calculator> getFigures() {
        return FIGURES;
    }

    public static String getPackageName(File child) {
        String packageName = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(child))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("package ")) {
                    packageName = line.substring(8, line.length() - 1).trim();
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
        return packageName + "." + child.getName().substring(0, child.getName().length() - 5);
    }

    public static void setUp(File dir) {
        File[] directoryListing = dir.listFiles();
        List<Calculator> result = new ArrayList<>();
        try {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    if (child.isDirectory()) {
                        setUp(child);
                    } else {
                        Class<?> clazz = Class.forName(getPackageName(child));
                        if (clazz.isAnnotationPresent(Figure.class)) {
                            Constructor<?> constructor = clazz.getDeclaredConstructor();
                            Object object = constructor.newInstance();
                            if (object instanceof Calculator) {
                                log.info("Add object to result " + object);
                                result.add((Calculator) object);
                            }
                        }
                    }
                }
            }
            FIGURES.addAll(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}