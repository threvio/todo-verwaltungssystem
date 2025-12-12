package de.projekt.todo.model;

public class Todo {
    private final int id;
    private final String text;

    public Todo(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() { return id; }
    public String getText() { return text; }

    @Override
    public String toString() {
        return text;
    }
}
