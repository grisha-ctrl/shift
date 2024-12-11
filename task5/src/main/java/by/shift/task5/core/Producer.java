package by.shift.task5.core;

import by.shift.task5.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer extends Thread {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    private final Storage storage;
    private final int producerTime;
    private final int producerId;

    public Producer(Storage storage, int producerId, int producerTime) {
        this.storage = storage;
        this.producerId = producerId;
        this.producerTime = producerTime;
    }

    @Override
    public void run() {
        try {
            int resourceId = 1;
            while (true) {
                Resource resource = new Resource(resourceId++);
                Thread.sleep(producerTime);
                storage.add(resource);
                log.info("Производитель {} произвёл ресурс {}", producerId, resource);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Производитель {} был прерван", producerId);
        }
    }
}
