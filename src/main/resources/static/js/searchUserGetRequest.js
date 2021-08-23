let searchUserFetchBtn = document.getElementById("searchUser");

searchUserFetchBtn.addEventListener("click", searchBtnHandler);

function searchBtnHandler() {
    // Instantiate an new XHR Object
    const xhr = new XMLHttpRequest();

    let email = $("#u_email").val();

    xhr.open("GET",
        window.location.origin + "/search_user/"+email, true);

    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    console.log(localStorage.getItem("jwt"));
    xhr.setRequestHeader("Authorization","Bearer "+localStorage.getItem("jwt").toString());
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){

            let user = xhr.response;
            user = JSON.parse(user);

            let tbody = document.getElementById("user_tbody");
            tbody.innerHTML = '';
            let tr = tbody.insertRow();
            let cell5 = tr.insertCell(0).innerText = user.id;
            let cell1 = tr.insertCell(1).innerText = user.name;
            let cell2 = tr.insertCell(2).innerText = user.email;
            let cell3 = tr.insertCell(3).innerText = user.mobileNumber;
            let cell4 = tr.insertCell(4).innerText = user.address;

            let cell6 = tr.insertCell(5).innerHTML = `<a th:href="@{edit}" class="btn btn-outline-secondary">\n` +
                `                      <i class="far fa-edit"></i> Edit\n` +
                `                    </a>`;

            let cell7 = tr.insertCell(5).innerHTML = `<a href="#"\n` +
                `                       data-toggle="modal"\n` +
                `                       data-target="#displayUserModal"\n` +
                `                    >\n` +
                `                      <i class="fas fa-eye"></i>Show`;

        }

    }

    xhr.send();
}