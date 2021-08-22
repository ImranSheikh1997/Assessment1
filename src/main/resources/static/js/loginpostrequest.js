
let logInFetchBtn = document.getElementById("logInbtn");

logInFetchBtn.addEventListener("click", buttonclickhandler);

function buttonclickhandler() {

    // Instantiate an new XHR Object
    const xhr = new XMLHttpRequest();

    xhr.open("POST",
        window.location.href + "login", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let user = {
        email: $("#l_email").val(),
        password: $("#l_password").val(),
    }

    xhr.onreadystatechange = function () {
        if (this.status == 200 || this.readyState == 4){
            localStorage.jwt = xhr.response;
           window.location.href = window.location.href+"dashboard";
        }
        else {
            console.log("Error ");
        }
    }
    xhr.send(JSON.stringify(user));
}