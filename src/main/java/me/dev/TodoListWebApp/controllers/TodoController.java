package me.dev.TodoListWebApp.controllers;

import me.dev.TodoListWebApp.db.TodoRepository;
import me.dev.TodoListWebApp.db.UserRepository;
import me.dev.TodoListWebApp.models.DTO.TodoDTO;
import me.dev.TodoListWebApp.models.Todo;
import me.dev.TodoListWebApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {


    @Autowired
    TodoRepository todoRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    private TodoService todoService;


    /**
     * Endpoint used to add a new to-do to the database, and user's to-do list
     *
     * @param todoDto
     * @return todo
     */
    @PostMapping("/add")
    public ResponseEntity<?> addTodo(@RequestBody TodoDTO todoDto){

        Todo todo = new Todo(todoService.getDate(todoDto.getDate()), userRepo.findUserById(todoDto.getUserId()), todoDto.getContent());

        if(!todoService.verifyDate(todo.getDate())){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(todo);
        }

        todoRepo.save(todo);
        todo.getUser().getTodos().add(todo);
        return ResponseEntity.ok(todo);
    }


    /**
     * Endpoint used to delete a todo
     *
     * @param todoID
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTodo(@RequestParam String todoID){
        Todo todo = todoRepo.findTodoById(todoID);

        if(todo == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find a todo with that ID");
        }
        
        todoRepo.delete(todo);
        return ResponseEntity.ok(todo);
    }


    /**
     * Endpoint used to edit existing todo
     *
     * @param todoDTO New todo information
     * @param todoId Old todo ID
     * @return
     */
    @PostMapping("/edit")
    public ResponseEntity<Todo> editTodo(@RequestBody TodoDTO todoDTO, @RequestParam String todoId){
        Todo newTodo = new Todo(todoService.getDate(todoDTO.getDate()), userRepo.findUserById(todoDTO.getUserId()), todoDTO.getContent());
        Todo todo = todoRepo.findTodoById(todoId);

        todo.editTodo(newTodo);
        todoRepo.save(todo);

        return ResponseEntity.ok(todo);
    }


    /**
     * Endpoint used to return all todos of a user id
     *
     * @param userId user's id
     * @return todos
     */
    @GetMapping("/get")
    public ResponseEntity<List<Todo>> getTodos(@RequestParam String userId){

        List<Todo> todos = todoRepo.getTodosByUserId(userId);
        return ResponseEntity.ok(todos);

    }


    /**
     * Endpoint used to toggle the state of a todo
     * @param todoId todo id
     * @return
     */
    @PostMapping("/toggleTodo")
    public ResponseEntity<?> toggleTodoState(@RequestParam String todoId){
        todoService.toggleTodoState(todoId);
        return ResponseEntity.ok().body("Successfully toggled");

    }

}
