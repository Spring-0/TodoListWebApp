
/*function getID(event) {

    event.preventDefault()
    // grab the input from the user regarding todo id ??
    const input = document.getElementById("userIdInput")

    const id = input.value;
    //loadTodos(id)
    console.log(id)

}*/

function getCookies() {
    const cookieValue = document.cookie.split(";").
    find((row) => row.startsWith("userId")).split("=")[1];

    console.log(cookieValue)
     return cookieValue
}

loadTodos(getCookies())
 function loadTodos(userId) {

    fetch("/todo/get?userId=" + userId, {
        method: 'GET', header: {
            'Content-Type': 'Application/Json'
        },
    })
    
        .then(response => {
            if (response.status === 200) {
                //console.log("theres data coming through")
                createTodoTable(response.json())

            } else {
                // Show Error
                alert("Unable to load Todo's")
            }
        })
=======
         .then(response  => {
             if (response.status === 200) {
                 createTodoTable(response.json())

             } else if (response.status === 404) {
                 // User does not have any todos
                 // TODO implement prompt to add todos
                 alert("You do not have any todos")

             } else {
                 alert("Something went wrong while loading your todos, try again later.")
             }
         }
        )

}


function createTodoTable(todos) {

    todos.then(response => {
        const table = document.getElementById("myTable")


        for (let i = 0; i < response.length; i++) {
        
    // Clear table contents
    table.innerHTML = ""

    for (let i = 0; i < response.length; i++) {


            const row = `<tr>
                                <td>${response[i].user.username}</td>
                                <td> ${response[i].content}</td>
                                <td> ${formatDate(response[i].date)}</td>
                                <td> <input  type="checkbox"></td>
                          </tr> `;
            table.innerHTML += row

        }
    });

}

