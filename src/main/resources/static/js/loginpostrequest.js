
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
            let response = xhr.response;
            response = JSON.parse(response);

            localStorage.setItem("jwt", response.jwt);
            localStorage.setItem("email", user.email);
            // console.log(localStorage.getItem("email"));
            if(response.jwt != null) {
                window.location.href = window.location.href + "dashboard";
            }
            else{
                window.alert("Invalid UserName/Password Supplied");
            }
        }
    }
    xhr.send(JSON.stringify(user));
}