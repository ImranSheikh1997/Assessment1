function editUser(email) {
    // Instantiate an new XHR Object
    const xhr = new XMLHttpRequest();

    xhr.open("POST",
        window.location.origin + "/update-user/"+email, true);

    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.setRequestHeader("authorization",localStorage.getItem("jwt").toString());
    let user = {
        name: $('#e_name').val(),
        address: $("#e_adress").val(),
        mobileNumber: $("#e_mobileNumber").val()
    }

    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            location.reload();
        }
    }
    xhr.send(JSON.stringify(user));

}