/*

function signUp() {

    $(document)
        .ready(
            function () {

                //SignUp Form
                $("#signUpForm").submit(
                    function (event) {
                        console.log("0");
                        // Prevent the form from submitting via the browser.
                        event.preventDefault();
                        ajaxPost();
                    }
                );

                function ajaxPost() {
                    // event.preventDefault();
                    console.log("01");
                    //Prepare Form Data
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


                    console.log("1");
                    //Do Post
                    $
                        .ajax(
                            {
                                method : "POST",
                                // type: "POST",
                                contentType: "application/json",
                                url: window.location + "do_register",
                                data: user,
                                dataType: 'json',
                                success: function (result) {
                                    console.log("2");
                                    if (result.status == "success") {
                                        $(".sign-up-container")
                                            .html(
                                                "<p style='background-color:#26b036; color:white; padding:20px 20px 20px 20px'>"
                                                + "Post Successfully! <br>"
                                                + "---> Congrats !!"
                                                + result.object.firstName
                                                + "</p>");
                                    } else {
                                        $(".sign-up-container")
                                            .html(
                                                "<strong>Error</strong>");
                                    }
                                    console.log(result);
                                },
                                error: function (e) {
                                    console.log("3");
                                    alert("Error!")
                                    console.log("Error: ", e);
                                }
                            }
                        );
                }

            }
        )
}*/
/*
const postreq = function() {
    console.log("2");
    document.getElementById("signUpbtn").addEventListener('click', function ()) { console.log("1")
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

        fetch(window.location.href + "do_register", {
            method: "POST",
            body: user,
            headers: {
                "Content-Type": 'application/json'

            }
        }).then(function (response) {
            console.log(response);

        })
    }
}
    postreq();
*/


let fetchBtn = document.getElementById("signUpbtn");

fetchBtn.addEventListener("click", buttonclickhandler);

function buttonclickhandler() {

    // Instantiate an new XHR Object
    const xhr = new XMLHttpRequest();

    // Open an obejct (GET/POST, PATH,
    // ASYN-TRUE/FALSE)
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

    xhr.onload = function () {
        if (this.status === 200){

        }
        else {
            console.log("Error ");
        }
    }
    xhr.send(JSON.stringify(user));
}