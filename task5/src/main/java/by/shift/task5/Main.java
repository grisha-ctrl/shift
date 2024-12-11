package by.shift.task5;

import by.shift.task5.core.Consumer;
import by.shift.task5.core.Producer;
import by.shift.task5.core.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(Main.class.getResourceAsStream("/application.properties"));

            int producerCount = Integer.parseInt(properties.getProperty("producerCount"));
            int consumerCount = Integer.parseInt(properties.getProperty("consumerCount"));
            int producerTime = Integer.parseInt(properties.getProperty("producerTime"));
            int consumerTime = Integer.parseInt(properties.getProperty("consumerTime"));
            int storageSize = Integer.parseInt(properties.getProperty("storageSize"));

            Storage storage = new Storage(storageSize);

            for (int i = 1; i <= producerCount; i++) {
                new Producer(storage, i, producerTime).start();
            }

            for (int i = 1; i <= consumerCount; i++) {
                new Consumer(storage, i, consumerTime).start();
            }

        } catch (IOException e) {
            log.error("Ошибка загрузки конфигурации", e);
        } catch (NumberFormatException e) {
            log.error("Некорректные значения конфигурации", e);
        }
    }
}
