package de.projekt.todo.ui;

import de.projekt.todo.model.Todo;
import de.projekt.todo.repository.TodoRepository;
import de.projekt.todo.repository.impl.SqliteTodoRepository;
import de.projekt.todo.service.TodoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainController {

    @FXML private TextField taskInput;
    @FXML private ListView<Todo> taskList;
    @FXML private Label statusLabel;

    private ObservableList<Todo> items;
    private TodoService service;

    @FXML
    public void initialize() {
        TodoRepository repo = new SqliteTodoRepository("todos.db");
        service = new TodoService(repo);

        items = FXCollections.observableArrayList();
        taskList.setItems(items);
        reload();
    }

    @FXML
    private void onAddTask() {
        Todo t = service.addTask(taskInput.getText());
        if (t != null) {
            taskInput.clear();
            reload();
            statusLabel.setText("Aufgabe hinzugefügt.");
        } else {
            statusLabel.setText("Bitte Text eingeben.");
        }
    }

    @FXML
    private void onDeleteTask() {
        Todo sel = taskList.getSelectionModel().getSelectedItem();
        if (sel != null) {
            service.delete(sel);
            reload();
            statusLabel.setText("Aufgabe gelöscht.");
        } else {
            statusLabel.setText("Bitte Aufgabe auswählen.");
        }
    }

    private void reload() {
        items.setAll(service.getAll());
    }
}
