$(document).ready(function	() {
	$(".logout").click(function(){
	    var confirmation = confirm("Are you sure you want to log out?");
	    if (confirmation == true) {
	        $.ajax({
				method: "GET",
				url: "logout",
				
				success: function(){
					window.location.href = "index.html";
				}
	        })
	    }
	})
})