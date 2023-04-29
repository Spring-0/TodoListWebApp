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