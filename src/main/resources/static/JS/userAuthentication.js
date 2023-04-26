
function login(event) {

    event.preventDefault();

    const username = document.getElementById("usernameTbx").value;
    const password = document.getElementById("passwordTbx").value;

    const data = {
        username: username,
        password: password
    };

    fetch('/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if(response.status === 200){
                window.location.replace("/todos");
            } else{
                document.getElementById("errorTbx").innerText = "Unable to authenticate with those credentials!";
            }
        })
        .catch(error => console.error(error));

}


function register(event) {

    event.preventDefault();

    const username = document.getElementById("usernameTbx").value;
    const password = document.getElementById("passwordTbx").value;

    const data = {
        username: username,
        password: password
    };

    fetch('/user/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if(response.status === 200){
                window.location.replace("/todos");
            } else{
                document.getElementById("errorTbx").innerText = "Unable to login.";
            }
        })
        .catch(error => console.error(error));

}

