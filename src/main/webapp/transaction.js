$(document).ready(function	() {
	getSessionId();
	$(".transaction").click(function() {
		document.getElementsByClassName("errormessage")[0].innerHTML = "";
		var source = $(".source").val()
		var target = $(".target").val();
		var amount = $(".amount").val();
		if(source == target){
			$(".errormessage").append("<p>The source and the target account must be different.</p>");
		} else if(amount == ""){
			$(".errormessage").append("<p>Invalid amount</p>");
		}else {
			/*$.ajax({
				method: "GET",
				url: "transition",
				data: { "source" : source, "target" : target, "amount" : amount }
				success: function(data){
					console.log(data);
				}
	        })*/
			console.log("ok");
		}
	})
})

function getSessionId(){
	$.ajax({
		method:"POST",
		url:"transaction",
		datatype: "json",
		success: function(data){
			getAllAccount(data)
		}
	})
}

function getAllAccount(id){
	console.log(id);
	$.ajax({
		method:"GET",
		url:"transaction",
		datatype: "json",
		success: function(data){
			console.log(data)
			$.each(data, function(ArrayID, BankAccount){
				if(BankAccount.userId == Number(id)){
					$(".source").append("<option selected>" + BankAccount.accountNumber + "</option>")
				}
				$(".target").append("<option selected>" + BankAccount.accountNumber + "</option>")
			})
		}
	})
}

