function getCookies() {
    try {
        let cookie = document.cookie.split(";").find((row) => row.startsWith("userId")).split("=")[1];
        return cookie
    } catch (TypeError ){ // check if user cookie exists
        document.location.assign("/login")
    }

}


function loadTodos(userId) {
    fetch("/todo/get?userId=" + userId, {
        method: 'GET', header: {
            'Content-Type': 'Application/Json'
        },
    })
        .then(response => {
                if (response.status === 200) {
                    populateTodoTable(response.json())

                } else if (response.status === 404) {
                    // User does not have any todos
                        //document.location.assign("/login")
                } else {
                    alert("Something went wrong while loading your todos, try again later.")
                }
            }
        )
}


function populateTodoTable(todos) {
    todos.then(data => {
        const table = document.getElementById("myTable");
        table.innerHTML = "";

        data.forEach(todo => {
            const row = document.createElement("tr");

            const contentCell = document.createElement("td");
            contentCell.textContent = todo.content;
            row.appendChild(contentCell);

            const dateCell = document.createElement("td");
            dateCell.textContent = formatDate(todo.date);
            row.appendChild(dateCell);

            const checkboxCell = document.createElement("td");
            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkboxCell.appendChild(checkbox);
            row.appendChild(checkboxCell);

            const deleteCell = document.createElement("td");
            const deleteButton = document.createElement("button");
            deleteButton.textContent = "Delete";
            deleteButton.addEventListener("click", () => {
                deleteTodo(todo.id);
                row.remove();
            });
            deleteCell.appendChild(deleteButton);
            row.appendChild(deleteCell);

            table.appendChild(row);
        });
    });
}


async function addRow() {
    const table = document.getElementById("myTable")

    const contentInput = document.createElement("input")
    contentInput.setAttribute("type", "text")

    const dateInput = document.createElement("input")
    dateInput.setAttribute("type", "text")

    const checkbox = document.createElement("input")
    checkbox.setAttribute("type", "checkbox")

    const row = document.createElement("tr")
    const tData = document.createElement("td")

    row.insertCell(0).appendChild(tData.appendChild(contentInput))
    row.insertCell(1).appendChild(tData.appendChild(dateInput))
    row.insertCell(2).appendChild(tData.appendChild(checkbox))

    table.appendChild(row)

}