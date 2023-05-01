


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
                 addRow()

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

async function addRow(){
    const table = document.getElementById("myTable")


    const contentInput = document.createElement("input")
    contentInput.setAttribute("type", "text" )

    const dateInput = document.createElement("input")
    dateInput.setAttribute("type", "text" )

    const checkbox = document.createElement("input")
    checkbox.setAttribute("type" ,"checkbox")

    const row = document.createElement("tr")
    const tData = document.createElement("td")

    row.insertCell(0).appendChild(tData.appendChild(contentInput))
    row.insertCell(1).appendChild(tData.appendChild(dateInput))
    row.insertCell(2).appendChild(tData.appendChild(checkbox))

    table.appendChild(row)

}