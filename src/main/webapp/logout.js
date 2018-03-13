$(document).ready(function () {
    //When logout button clicked, ask a confirmation to log out
    //If confirmation is true, send a request to server
    //Then redirecting to index.html
    $(".logout").click(function () {
        var confirmation = confirm("Are you sure you want to log out?");
        if (confirmation == true) {
            $.ajax({
                method: "GET",
                url: "logout",

                success: function () {
                    window.location.href = "index.html";
                }
            })
        }
    })
});