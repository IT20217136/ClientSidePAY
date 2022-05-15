package com;

import java.sql.*;

public class Payment
{
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
 
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "Sathira@2000");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String readPayment()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Name</th><th>Address</th><th>ZIP code</th><th>EX Date</th><th>CVV</th>"+"<th>Update</th><th>Remove</th></tr>";
 
			String query = "select * from payment";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
 
			// iterate through the rows in the result set
			while (rs.next())
			{
				String name = rs.getString("name");
				String address = rs.getString("address");
				String zipcode = rs.getString("zipcode");
				String cardnumber = rs.getString("cardnumber");
				String exdate = rs.getString("exdate");
				String cvv = rs.getString("cvv");
 
				// Add into the html table
				output += "<tr><td><input id='cardnumber'name='cardnumber'type='hidden' value='" + cardnumber+ "'>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + zipcode + "</td>";
				output += "<td>" + exdate + "</td>";
				output += "<td>" + cvv + "</td>";

 
				// buttons
				output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-cardnumber='"+ cardnumber + "'>" + "</td></tr>";
			}
 
			con.close();
 
			// Complete the html table
			output += "</table>";
		}
 
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
 
		return output;
	}
	
	public String insertPayment(String name, String address,String zipcode, String cardnumber,String exdate, String cvv )
    {
		String output = "";

		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
 
			// create a prepared statement
			String query = " insert into payment(`name`,`address`,`zipcode`,`cardnumber`,`exdate`, `cvv`)"+ " values (?, ?, ?, ?, ?)";
		 
			PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, address);
			preparedStmt.setString(3, zipcode);
			preparedStmt.setString(4, cardnumber);
			preparedStmt.setString(5, exdate);
			preparedStmt.setString(5, cvv);
		 
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPayment + "\"}";
		 }
		
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			 System.err.println(e.getMessage());
		 }
		
		 return output;
		 
		 }
	
		 public String updatePayment(String name, String address, String zipcode, String cardnumber, String exdate, String cvv)
		 {
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for updating.";
				 }
				 
				 // create a prepared statement
				 String query = "UPDATE payment SET name=?,address=?,zipcode=?,exdate=?,cvv=? WHERE cardnumber=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
				 // binding values
				    preparedStmt.setString(1, name);
					preparedStmt.setString(2, address);
					preparedStmt.setString(3, zipcode);
					preparedStmt.setString(4, exdate);
					preparedStmt.setString(5, cvv); 
		
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
		
				 String newPayment = readPayment();
				 output = "{\"status\":\"success\", \"data\": \"" +
				 newPayment + "\"}";
		 }
			 
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		return output;
	}
		
	public String deletePayment(String cardnumber)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for deleting.";
			 }
		 
			 // create a prepared statement
			 String query = "delete from payment where cardnumber=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			 // binding values
			 preparedStmt.setInt(4, Integer.parseInt(cardnumber));
		 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newPayment = readPayment();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newPayment + "\"}";
		 }
		 
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
		 
		 }
	}
		 