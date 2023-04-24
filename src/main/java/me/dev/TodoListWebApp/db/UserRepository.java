package me.dev.TodoListWebApp.db;

import me.dev.TodoListWebApp.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, String>{


}
