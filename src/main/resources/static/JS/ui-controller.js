

function deleteTodo(todoId){

    fetch(`/todo/delete?todoID=${todoId}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        },
    })
        .then(response => {
            if(response.ok){
                console.log("Successfully deleted todo.");
            } else {
                console.log("Error deleting todo", response.status);
            }
        })
        .catch(error => {
            console.log("Network error:", error);
        })

}
