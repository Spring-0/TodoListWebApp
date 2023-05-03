/**
 * Helper function to format a date string for readability.
 *
 * @param dateString
 * @returns {string}
 */
function formatDate(dateString){
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

    const tableBody = document.getElementById("myTable")

    const rows = tableBody.getElementsByTagName("tr");

    let todos = []
    let todo = {"content": "", "date": "", "userId": "", "completed": ""}

    for (let i = 0; i < rows.length; i++) {
        let row = rows[i]

        for (let j = 0; j < 3; j++) {

            let key = ""

            switch (j) {
                case 0:
                    key = "content"
                    break;
                case 1:
                    key = "date"
                    break;
                case 2:
                    key = "completed"
                    let bool = row.getElementsByTagName("td").item(j).getElementsByTagName("input").item(0).checked.toString()
                    todo[key] = bool
                    continue;
                    break;
            }

            todo[key] = row.getElementsByTagName("td").item(j).getElementsByTagName("input").item(0).value
            todo["userId"] = getUserIdFromCookie();

        }

        todos.push(todo)
        todo = {"content": "", "date": "", "userId": "", "completed": ""}
    }


    fetch("todo/add", {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(todos)
    })
        .then(response => {
            if (response.ok) {
                console.log("Successfully saved the entry to the db")
                console.log(response)
            }
        })

}


function getUserIdFromCookie() {
    return document.cookie.split(";").find((row) => row.startsWith("userId")).split("=")[1];
}
