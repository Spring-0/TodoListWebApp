package me.dev.TodoListWebApp.models.DTO;


public class TodoDTO {

    private String date;
    private String userId;
    private String content;
    private Boolean checked;


    public TodoDTO(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getChecked(){
        return this.checked;
    }

    public void setChecked(Boolean checked){
        this.checked = checked;
    }
}
