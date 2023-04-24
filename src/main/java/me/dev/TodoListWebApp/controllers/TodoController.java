package me.dev.TodoListWebApp.controllers;

import me.dev.TodoListWebApp.db.TodoRepository;
import me.dev.TodoListWebApp.db.UserRepository;
import me.dev.TodoListWebApp.models.DTO.TodoDTO;
import me.dev.TodoListWebApp.models.Todo;
import me.dev.TodoListWebApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Todo addTodo(@RequestBody TodoDTO todoDto){

        Todo todo = new Todo(todoService.getDate(todoDto.getDate()), userRepo.findUserById(todoDto.getUserId()), todoDto.getContent());

        todoRepo.save(todo);
        todo.getUser().getTodos().add(todo);
        return todo;
    }


    /**
     * Endpoint used to delete a todo
     *
     * @param todoID
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity<Todo> deleteTodo(@RequestParam String todoID){
        Todo todo = todoRepo.findTodoById(todoID);
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

}
