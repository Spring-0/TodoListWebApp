


function getCookies() {
    const cookieValue = document.cookie.split(";").
    find((row) => row.startsWith("userId")).split("=")[1];
     return cookieValue
}

loadTodos(getCookies())
 function loadTodos(userId) {

    fetch("/todo/get?userId=" + userId, {
        method: 'GET', header: {
            'Content-Type': 'Application/Json'
        },
    })
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

    todos.then(response  =>{
        const table = document.getElementById("myTable")

        // Clear table contents
        table.innerHTML = ""

        for (let i = 0; i < response.length; i++) {

            const row = `<tr>
                                <td> ${response[i].content}</td>
                                <td> ${formatDate(response[i].date)}</td>
                                <td> <input  type="checkbox"></td>
                          </tr> `;
            table.innerHTML += row

        }
    });

}

function addRow(){
    const table = document.getElementById("myTable")

    const row = `<tr>
                                <td ><input type="text" placeholder="Your goal"> </td>
                                <td><input type="text" placeholder="Enter a date"> </td>
                                <td> <input  type="checkbox"></td>
                          </tr>`;
    table.innerHTML += row

}