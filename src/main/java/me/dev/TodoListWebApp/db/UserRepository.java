package me.dev.TodoListWebApp.db;

import me.dev.TodoListWebApp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <User, String>{

}
