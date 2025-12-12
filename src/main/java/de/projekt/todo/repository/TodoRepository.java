package de.projekt.todo.repository;

import de.projekt.todo.model.Todo;
import java.util.List;

public interface TodoRepository {
    void init();
    Todo add(String text);
    List<Todo> findAll();
    void deleteById(int id);
}
