$(document).ready(function	() {
	/*history.pushState(null, null, location.href);
	window.onpopstate = function () {
		history.go(1);
	}*/
	
	//When click submit button
	//The hidden field content set visibility to hidden
	//Get password and user name from fields
	//If password field is empty show error message
	//Else send the password and the user name to server side
	//Handle the data
	//If data equal "error" set hidden field visibility to visible, else redirect to bankaccount.html
	$(".submit").click(function() {
		document.getElementById('hiddenfield').style.visibility = 'hidden';
		var username = $(".username").val();
		var password = $(".password").val();
		if(password.length < 1){
			document.getElementById('hiddenfield').style.visibility = 'visible';
		} else {
			$.ajax({
				method: "GET",
				url: "login",
				datatype: "json",
				data: { "username": username, "password": password },
				success: function(data){
					if(data == "error"){
						document.getElementById('hiddenfield').style.visibility = 'visible';
					} else {
						window.location.href = "bankaccount.html";
					}
				}
			})
		}
	});
})