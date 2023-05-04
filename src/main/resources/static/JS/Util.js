/**
 * Helper function to format a date string for readability.
 *
 * @param dateString
 * @returns {string}
 */
function formatDate(dateString) {
    const date = new Date(dateString);
    const options = {
        year: "numeric",
        month: "long",
        day: "numeric",
        hour: 'numeric',
        minute: 'numeric',
        hour12: true,
    }
    return date.toLocaleDateString("en-US", options)
}


async function saveTodo() {
    const tableBody = document.getElementById("myTable");
    const rows = tableBody.getElementsByTagName("tr");
    const todos = [];

    for (let i = 0; i < rows.length; i++) {
        const row = rows[i];
        const cells = row.getElementsByTagName("td");

        if(cells[0].querySelector("input") === null){continue; }

        const todo = {
            content: cells[0].querySelector("input").value,
            date: cells[1].querySelector("input").value,
            userId: getUserIdFromCookie(),
            checked: cells[2].querySelector("input").checked,
        };

        // Format the date
        cells[1].querySelector("input").value = formatDate(cells[1].querySelector("input").value)

        if (todo.userId) {
            todos.push(todo);
        }

        for (let j = 0; j < cells.length; j++) {
            const cell = cells[j];
            const input = cell.querySelector("input");

            if (input) {
                cell.innerHTML = input.value;
                if (input.parentNode === cell) {
                    cell.removeChild(input);
                }
                if (cell.getAttribute("data-key") === "date") {
                    cell.textContent = formatDate(cell.textContent);
                }
            }
        }
    }
    console.log(JSON.stringify(todos))

    await fetch('/todo/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(todos)
    })

}


function getUserIdFromCookie() {
    return document.cookie.split(";").find((row) => row.startsWith("userId")).split("=")[1];
}
