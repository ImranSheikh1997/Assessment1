function displayUserBtnHandler(email) {

    // console.log("working");
    // Instantiate an new XHR Object
    const xhr = new XMLHttpRequest();
    // console.log(email);

    xhr.open("GET",
        window.location.origin + "/search_user/" + email, true);

    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let user = xhr.response;
            user = JSON.parse(user);
            document.getElementById("d_name").value = user.name;
            document.getElementById("d_email").value = user.email;
            document.getElementById("d_mobile_number").value = user.mobileNumber;
            document.getElementById("d_addess").value = user.address;
        }
    }
    xhr.send();
}