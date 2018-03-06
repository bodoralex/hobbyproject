$(document).ready(function	() {
	getSessionId();
	
	$(".choose").click(function(){
		document.getElementsByClassName("historytable")[0].innerHTML = "";
		var sourceAccount = $(".sourceaccount").val();
		$.ajax({
			method: "GET",
			url: "accounthistory",
			datatype: "json",
			data: { "sourceAccount" : sourceAccount },
			success: function(data){
				$(".historytable").append("<tr><th>Source/target account</th>" +
						"<th>Currency</th><th>Amount</th>" +
						"<th>Transaction type</th></tr>")
				$.each(data, function(ArrayID, AccountHistory){
					if(AccountHistory.accountNumber == sourceAccount){
						$(".historytable").append("<tr><td>" + AccountHistory.sourceTargetAccount + "</td>" +
								"<td>" + AccountHistory.currency + "</th><td>" + AccountHistory.amount + "</td>" +
								"<td>" + AccountHistory.transactionType + "</td></tr>");
					}
					
				})
			}
		})
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

function getAccount(id){
	$.ajax({
		method: "GET",
		url: "bankaccount",
		datatype: "json",
		success: function(data){
			if(data.length == 0){
				document.getElementsByClassName("historytable")[0].innerHTML = "";
				document.getElementsByClassName("inputField")[0].innerHTML = "";
				$(".errormessage").append("<h3>You haven't got any bank account.</h3>");
			} else {
				$.each(data, function(ArrayID, BankAccount){
					$(".sourceaccount").append("<option>" + BankAccount.accountNumber + "</option>");
				})
			}
		}
	})
}

