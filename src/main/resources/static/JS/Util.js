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

// Todo: save user's todos to database
function saveTodoToDatabase(todo){

    const table = document.getElementById("table").rows[0]
    let array = new Array()
    for( let i =0; i < table.length; i++){
        console.log( table[i])
    }

}
