$(document).ready(function	() {
	history.pushState(null, null, location.href);
	window.onpopstate = function () {
		history.go(1);
	}
	$(".submit").click(function() {
		document.getElementsByClassName("errormessage")[0].innerHTML = "";
		var username = $(".username").val();
		var password = $(".password").val();
		if(password.length < 1){
			console.log("Username or password invalid")
		} else {
			$.ajax({
				method: "GET",
				url: "login",
				datatype: "json",
				data: { "username": username, "password": password },
				success: function(data){
					if(data == "notok"){
						$(".errormessage").append("<p>Username or password invalid.<p>");
					} else {
						window.location.href = "bankaccount.html";
					}
				}
			})
		}
	});
})