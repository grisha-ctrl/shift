package by.shift.task5.model;

public class Resource {
    private final int id;

    public Resource(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Resource{id=" + id + '}';
    }
}
