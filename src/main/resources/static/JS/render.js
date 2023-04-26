
function loadTodos(userId){
    fetch("/todo/get?todo="+ userId, {
        method: 'GET',
        header: {
            'Content-Type': 'Application/Json'
        },
    })
        .then(response => {
            if(response.status === 200){
                const table = document.getElementById("todoTable");
            } else{
                // Show Error
            }
        })
}

