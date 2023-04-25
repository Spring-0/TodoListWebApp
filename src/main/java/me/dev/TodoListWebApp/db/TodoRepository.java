package me.dev.TodoListWebApp.db;

import me.dev.TodoListWebApp.models.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TodoRepository extends CrudRepository<Todo, String> {
    Todo findTodoById(String todoId);
    List<Todo> getTodosByUserId(String userId);
}
