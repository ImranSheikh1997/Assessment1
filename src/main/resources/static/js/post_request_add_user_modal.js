console.log("inside js file");

let fetchBtn = document.getElementById("addUserModalButton");

fetchBtn.addEventListener("click", addUserHandler);

function addUserHandler() {

    console.log("inside js Function");

    // Instantiate an new XHR Object
    const xhr = new XMLHttpRequest();
    console.log("inside function");

    xhr.open("POST",
        window.location.origin + "/add_user", true);

    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    let user = {
        name: $("#name").val(),
        email: $("#email").val(),
        mobileNumber: $("#mobile_number").val(),
        address: $("#address").val()
    }

    xhr.send(JSON.stringify(user));
}
