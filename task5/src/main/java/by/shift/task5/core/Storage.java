package by.shift.task5.core;

import by.shift.task5.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class Storage {
    private static final Logger log = LoggerFactory.getLogger(Storage.class);
    private final int capacity;
    private final Queue<Resource> resources = new LinkedList<>();

    public Storage(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void add(Resource resource) throws InterruptedException {
        while (resources.size() >= capacity) {
            log.info("Склад полон. Производитель ожидает.");
            wait();
        }
        resources.add(resource);
        log.info("Ресурс {} добавлен на склад. Текущий размер: {}", resource, resources.size());
        notifyAll();
    }

    public synchronized Resource remove() throws InterruptedException {
        while (resources.isEmpty()) {
            log.info("Склад пуст. Потребитель ожидает.");
            wait();
        }
        Resource resource = resources.poll();
        log.info("Ресурс {} изъят со склада. Текущий размер: {}", resource, resources.size());
        notifyAll();
        return resource;
    }
}
