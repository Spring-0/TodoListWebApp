
// test data
const testUserData = [
    {
        id: "221ed967-d6db-485b-99b9-541f84397677",
        date: "2020-02-02T06:11:11.000+00:00",
        user: {
            id: "9430bf7a-6573-4680-96b1-8339d0c85e6f",
            username: "king",
            password: "king",
        },
        content: "wake up",
    },
    {
        id: "221ed967-d6db-485b-99b9-541f84397677",
        date: "2020-02-02T06:11:11.000+00:00",
        user: {
            id: "9430bf7a-6573-4680-96b1-8339d0c85e6f",
            username: "king",
            password: "king",
        },
        content: "wake up",
    },
    {
        id: "221ed967-d6db-485b-99b9-541f84397677",
        date: "2020-02-02T06:11:11.000+00:00",
        user: {
            id: "9430bf7a-6573-4680-96b1-8339d0c85e6f",
            username: "king",
            password: "king",
        },
        content: "wake up",
    },
    // Add more todo objects as needed...
];
function getID(){
    const formElement = document.getElementById("idForm")
    formElement.addEventListener("submit", (event) => {
        event.preventDefault()
        // grab the input from the user regarding todo id ??
        const input = document.getElementById("userIdInput")

        const id = input.value;
        loadTodos(id)
    });
}
function loadTodos(userId){
    fetch("/todo/get?todo="+ userId, {
        method: 'GET',
        header: {
            'Content-Type': 'Application/Json'
        },
    })
        .then(response => {
            if(response.status === 200){
                       createTodoTable(userId)
            } else{
                // Show Error
                alert("Unable to load Todos")
            }
        })
}

// 
//message of todo
// show data
// give check box option


function createTodoTable(todos) {

    const table= document.getElementById("myTable")

    for(let i= 0; i < todos.length; i++){
        const row = table.insertRow()

        const user = row.insertCell(0)
        user.textContent = todos.entries()

        const content = row.insertCell(1)
        content.textContent = todos.content

        const date = row.insertCell(2)
        //todo: ??
        // date.textContent = todos.getDate()
        date.textContent = "2020-02-20"

        const complete = row.insertCell(3)
        const checkBox = document.createElement("input");
        checkBox.type="checkbox"
        complete.appendChild(checkBox)

    }




    }
/*
var row = `<tbody>
                                <td>${todos[i].id}</td>
                                <td> ${todos[i].content}</td>
                                <td>${todos[i].getDate()}</td>
                                <td>${todos[i].content}</td>
                           </tbody>`
table.setAttribute("row", row);*/
//TODO: have different todos for a user