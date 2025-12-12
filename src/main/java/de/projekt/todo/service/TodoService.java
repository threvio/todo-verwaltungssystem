package de.projekt.todo.service;

import de.projekt.todo.model.Todo;
import de.projekt.todo.repository.TodoRepository;

import java.util.List;

public class TodoService {

    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
        this.repo.init();
    }

    public Todo addTask(String text) {
        if (text == null || text.trim().isEmpty()) return null;
        return repo.add(text.trim());
    }

    public List<Todo> getAll() {
        return repo.findAll();
    }

    public void delete(Todo todo) {
        if (todo != null) repo.deleteById(todo.getId());
    }
}
