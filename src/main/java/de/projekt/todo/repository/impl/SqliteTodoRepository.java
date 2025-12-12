package de.projekt.todo.repository.impl;

import de.projekt.todo.model.Todo;
import de.projekt.todo.repository.TodoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteTodoRepository implements TodoRepository {

    private final String url;

    public SqliteTodoRepository(String dbFileName) {
        this.url = "jdbc:sqlite:" + dbFileName;
    }

    @Override
    public void init() {
        String sql = """
                CREATE TABLE IF NOT EXISTS todos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    text TEXT NOT NULL
                );
                """;
        try (Connection c = DriverManager.getConnection(url);
             Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Todo add(String text) {
        String sql = "INSERT INTO todos(text) VALUES(?)";
        try (Connection c = DriverManager.getConnection(url);
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, text);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return new Todo(rs.getInt(1), text);
            }
            throw new SQLException("No ID");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<>();
        String sql = "SELECT id, text FROM todos ORDER BY id DESC";
        try (Connection c = DriverManager.getConnection(url);
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Todo(rs.getInt("id"), rs.getString("text")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM todos WHERE id=?";
        try (Connection c = DriverManager.getConnection(url);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
