function deleteUser(email) {
    //Instantiation
    const xhr = new XMLHttpRequest();

    xhr.open("DELETE",
        window.location.origin + "/delete-user", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    console.log(localStorage.getItem("jwt"));
    xhr.setRequestHeader("Authorization","Bearer " + localStorage.getItem("jwt"));
    xhr.send(email);
}