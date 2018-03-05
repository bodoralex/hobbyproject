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
			$.ajax({
				method: "GET",
				url: "transfer",
				datatype: "json",
				data: { "source" : source, "target" : target, "amount" : amount },
				success: function(data){
					$(".errormessage").append("<p>" + data + "</p>");
				}
	        })
		}
	})
})

function getSessionId(){
	$.ajax({
		method:"POST",
		url:"transactionpage",
		datatype: "json",
		success: function(data){
			getAccount(data)
		}
	})
}

function getAllAccount(id){
	console.log(id);
	$.ajax({
		method:"GET",
		url:"transactionpage",
		datatype: "json",
		success: function(data){
			console.log(data)
			$.each(data, function(ArrayID, BankAccount){
				if(BankAccount.userId == Number(id)){
					$(".source").append("<option>" + BankAccount.accountNumber + "</option>")
				}
				$(".target").append("<option>" + BankAccount.accountNumber + "</option>")
			})
		}
	})
}
function getAccount(id){
	$.ajax({
		method: "GET",
		url: "bankaccount",
		datatype: "json",
		success: function(data){
			if(data.length == 0){
				document.getElementsByClassName("table")[0].innerHTML = "";
				$(".errormessage").append("<h3>You haven't got any bank account.</h3>");
			} else {
				getAllAccount(id);
			}
		}
	})
}

