$(document).ready(function	() {
	/*history.pushState(null, null, location.href);
	window.onpopstate = function () {
		history.go(1);
	}*/
	
	//When click submit button
	//The error message content set empty
	//Get password and user name from fields
	//If password field is empty show error message
	//Else send the password and the user name to server side
	//Handle the data
	//If data.password not equal empty string redirect to bankaccount.html, else show error message
	$(".submit").click(function() {
		document.getElementsByClassName("errormessage")[0].innerHTML = "";
		var username = $(".username").val();
		var password = $(".password").val();
		if(password.length < 1){
			$(".errormessage").append("<p>Username or password invalid</p>")
		} else {
			$.ajax({
				method: "GET",
				url: "login",
				datatype: "json",
				data: { "username": username, "password": password },
				success: function(data){
					if(data.password == ""){
						$(".errormessage").append("<p>Username or password invalid.<p>");
					} else {
						window.location.href = "bankaccount.html";
					}
				}
			})
		}
	});
})