function jwtAuthorization() {
    try {
        fetch(window.location.origin + '/verify-token', {
            method: 'POST',
            headers: {
                Authorization: 'Bearer ' + sessionStorage.getItem("jwt").toString(),
                'Content-Type': 'application/json'
            },

        }).then(response => {
        })
            .catch((err) => {
                document.write('<!--');

                document.write('-->');
                document.write(`<span class="bg-danger" > You are not Authorized To Access This Page</span>`);
            })
    }
    catch (e) {
        document.write('<!--');

        document.write('-->');
        document.write(`<span class="bg-danger" > You are not Authorized To Access This Page</span>`);
    }


}