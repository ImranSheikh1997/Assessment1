// let firstName = document.getElementById("p_firstName");

let elements = {
    firstName : document.getElementById("p_firstName"),
    lastName : document.getElementById("p_lastName"),
    email : document.getElementById("p_email"),
    address : document.getElementById("p_address"),
    gender : document.getElementById("p_gender"),
    state : document.getElementById("p_state"),
    country : document.getElementById("p_country"),
    zipCode: document.getElementById("p_zipCode")
}


function adminBody() {

    try {
        fetch(window.location.origin + '/findadmin', {
            method: 'GET',
            headers: {
                Authorization: 'Bearer ' + sessionStorage.getItem("jwt").toString()
            }

        }).then(response => response
        ).then(res => res.json()).then(res => {
                if (res != null) {
                    elements.firstName.value = res.firstName;
                    elements.lastName.value = res.lastName;
                    elements.email.value = res.email;
                    elements.address.value = res.addressLine;
                    elements.gender.value = res.gender;
                    elements.state.value = res.state;
                    elements.country.value = res.country;
                    elements.zipCode.value = res.zipCode;
                }
            }
        )
            .catch((err) => {
                document.write('<!--');

                document.write('-->');
                document.write(`<span class="bg-danger" > You are not Authorized To Access This Page</span>`);
                console.error('Fetch Error : ', err);
            })

    } catch (e) {
        document.write('<!--');

        document.write('-->');
        document.write(`<span class="bg-danger" > You are not Authorized To Access This Page</span>`);
    }
}
function editAdmin() {
    let user = {
        firstName: elements.firstName.value,
        lastName: elements.lastName.value,
        email: elements.email.value,
        addressLine: elements.address.value,
        gender: elements.gender.value,
        state: elements.state.value,
        country: elements.country.value,
        zipCode: elements.zipCode.value
    }

    fetch(window.location.origin + '/update-admin', {
        method: 'PUT',
        headers: {
            Authorization: 'Bearer ' + sessionStorage.getItem("jwt").toString(),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user),
    }).then(response => {

    } )
        .catch((err) => {
        console.log('Fetch Error : ', err);
    } )
}


function changePassword() {
    let currentPassword = document.getElementById("currentPassword").value;
    let newPassword = document.getElementById("newPassword").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    if(newPassword ===  confirmPassword ){
        fetch(window.location.origin + "/change-password?" + new URLSearchParams({
            currentPassword: currentPassword,
            newPassword: newPassword
        }), {
            method: "PUT",
            headers: {
                Authorization: 'Bearer ' + sessionStorage.getItem("jwt").toString(),
                'Content-Type': 'application/json'
            }
        }).then(response => {

        }).
            catch((err) => {
                console.log('fetch error : ',err);
        })
    }
    else{
        document.getElementById("mainDiv").innerHTML = `<span class="bg-danger"> invalid confirm password </span>`
    }
}