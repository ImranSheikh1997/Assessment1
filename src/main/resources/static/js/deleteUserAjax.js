function deleteUser(email) {
    //Instantiation
    const xhr = new XMLHttpRequest();

    xhr.open("DELETE",
        window.location.origin + "/delete-user", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(email);
}