<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payments Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/payments.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

	<h1>Payments Management </h1>

	<form id="formPayment" name="formPayment">
 		Name :
 		<input id="cardnumber" name="cardnumber" type="text" class="form-control form-control-sm">
 		<br> 
 		Address:
		<input id="address" name="address" type="text" class="form-control form-control-sm">
 		<br> 
 		ZIP Code:
 		<input id="zipcode" name="zipcode" type="text" class="form-control form-control-sm">
 		<br> 
 		Card Number:
		<input id="cardnumber" name="cardnumber" type="text" class="form-control form-control-sm">
 		<br>
 		Ex Date:
		<input id="exdate" name="exdate" type="text" class="form-control form-control-sm">
 		<br>
 		CVV:
		<input id="cvv" name="cvv" type="password" class="form-control form-control-sm">
 		<br>
 		
 		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 		
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>

	<br>
	<div id="divPaymentsGrid">
 		<%
 			Payment paymentObj = new Payment();
 			out.print(paymentObj.readPayment());
 		%>
	</div>
</div> </div> </div>
</body>
</html>>