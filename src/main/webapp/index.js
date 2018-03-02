$(document).ready(function	() {
	$(".submit").click(function() {
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
					if(data == "ok"){
						alert(data);
					} else {
						$(".errormessage").append("<p>" + data + "<p>");
					}
				}
			})
		}
	});
})