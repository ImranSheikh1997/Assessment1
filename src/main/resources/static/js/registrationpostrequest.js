let fetchBtn = document.getElementById("signUpbtn");

fetchBtn.addEventListener("click", buttonclickhandler);

function buttonclickhandler() {

    // Instantiate an new XHR Object
    const xhr = new XMLHttpRequest();

    xhr.open("POST",
        window.location.href + "do_register", true);

    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let user = {
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        email: $("#email").val(),
        addressLine: $("#addressLine").val(),
        gender: $("#gender").val(),
        password: $("#password").val(),
        state: $("#state").val(),
        country: $("#country").val(),
        zipCode: $("#zipCode").val()
    }

    xhr.onreadystatechange = function () {
        console.log();
        if (this.status == 200 && this.readyState == 4){

            document.getElementById("msg").innerText = "User Successfully registered."
            container.classList.remove('right-panel-active');
        }
    }

    xhr.send(JSON.stringify(user));
}