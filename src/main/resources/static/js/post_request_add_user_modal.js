let fetchBtn = document.getElementById("addUserModalButton");

fetchBtn.addEventListener("click", addUserHandler);

function addUserHandler() {

    // Instantiate an new XHR Object
    const xhr = new XMLHttpRequest();

    xhr.open("POST",
        window.location.origin + "/add_user", true);

    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    console.log(localStorage.getItem("jwt"));
     xhr.setRequestHeader("Authorization","Bearer " + localStorage.getItem("jwt"));
    let user = {
        name: $("#name").val(),
        email: $("#email").val(),
        mobileNumber: $("#mobile_number").val(),
        address: $("#address").val()
    }

    xhr.onreadystatechange = function () {
        if (this.status == 200 && this.readyState == 4){
             // window.location.reload();
        }
    }

    xhr.send(JSON.stringify(user));
}
