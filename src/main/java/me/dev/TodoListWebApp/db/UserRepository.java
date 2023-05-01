package me.dev.TodoListWebApp.db;

import me.dev.TodoListWebApp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findUserById(String userID);
    User findUserByUsernameAndPassword(String username, String password);

}
