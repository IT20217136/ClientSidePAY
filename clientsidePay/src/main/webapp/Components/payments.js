$(document).ready(function()
{
	 $("#alertSuccess").hide();
 	 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();

	// Form validation-------------------
	var status = validatePaymentForm();
	if (status != true)
	{
		 $("#alertError").text(status);
 		 $("#alertError").show();
 		 return;
 	}
 	
	// If valid-------------------------
 	var type = ($("#cardnumber").val() == "") ? "POST" : "PUT";

	$.ajax(
 	{
 		url : "PaymentAPI",
 		type : type,
 		data : $("#formPayment").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onPaymentSaveComplete(response.responseText, status);
 		}
 	}); 
 });

function onPaymentSaveComplete(response, status)
	{
		if (status == "success")
		{
			 var resultSet = JSON.parse(response);
 			 if (resultSet.status.trim() == "success")
			 {
 				$("#alertSuccess").text("Successfully saved.");
 				$("#alertSuccess").show();
 				$("#divItemsGrid").html(resultSet.data);
 			 } 
 			 else if (resultSet.status.trim() == "error")
			 {
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			 }
 		} 
 		else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} 
 		else
 		{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
 		}
		$("#cardnuber").val("");
 		$("#formPayment")[0].reset();
}

	// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
		 $("#cardnumber").val($(this).data("cardnumber"));
		 $("#name").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#address").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#zipcode").val($(this).closest("tr").find('td:eq(3)').text());
		 
		 $("#exdate").val($(this).closest("tr").find('td:eq(4)').text());
 		 $("#cvv").val($(this).closest("tr").find('td:eq(5)').text());
	});
	
	
	
	$(document).on("click", ".btnRemove", function(event)
	{
 		$.ajax(
 		{
 			url : "PaymentAPI",
 			type : "DELETE",
 			data : "cardnumber=" + $(this).data("cardnumber"),
 			dataType : "text",
 			complete : function(response, status)
 			{
 				onPaymentDeleteComplete(response.responseText, status);
 			}
 		});
	});


	function onPaymentDeleteComplete(response, status)
	{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			if (resultSet.status.trim() == "success")
 			{
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				$("#divItemsGrid").html(resultSet.data);
 			} 
 			else if (resultSet.status.trim() == "error")
 			{
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			}
 		} 
 		else if (status == "error")
 		{
 				$("#alertError").text("Error while deleting.");
 				$("#alertError").show();
 		} 
 		else
 		{
 				$("#alertError").text("Unknown error while deleting..");
 				$("#alertError").show();
 		}
}
	

	// CLIENT-MODEL================================================================
	function validatePaymentForm()
	{
		
		// NAME
		if ($("#name").val().trim() == "")
 		{
 			return "Insert Name.";
 		}

		// address-------------------------------
		if ($("address").val().trim() == "")
 		{
 			return "Insert Item Address.";
 		}
 		

		return true;
	}
	
	
	
	
