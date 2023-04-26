package me.dev.TodoListWebApp.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Date date;

    @OneToOne
    private User user;

    private String content;

    private boolean completed;

    public Todo(Date date, User user, String content){
        this.date = date;
        this.user = user;
        this.content = content;
        this.completed = false;
    }

    public Todo(){

    }


    public void editTodo(Todo todo){
        this.date = todo.date;
        this.content = todo.content;
    }

    public void toggleCompletion(){
        this.completed = !this.completed;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
