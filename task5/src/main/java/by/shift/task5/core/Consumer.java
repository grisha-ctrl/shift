package by.shift.task5.core;

import by.shift.task5.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer extends Thread {
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    private final Storage storage;
    private final int consumerTime;
    private final int consumerId;

    public Consumer(Storage storage, int consumerId, int consumerTime) {
        this.storage = storage;
        this.consumerId = consumerId;
        this.consumerTime = consumerTime;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(consumerTime);
                Resource resource = storage.remove();
                log.info("Потребитель {} потребил ресурс {}", consumerId, resource);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Потребитель {} был прерван", consumerId);
        }
    }
}

