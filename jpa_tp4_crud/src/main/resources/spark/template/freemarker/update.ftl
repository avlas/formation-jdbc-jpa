<!DOCTYPE html>
<html>
	<head>
			<style>
				table {
				    font-family: arial, sans-serif;
				    border-collapse: collapse;
				}
				
				td, th {
				    border: 1px solid #dddddd;
				    text-align: left;
				    padding: 8px;
				}
			</style>
	</head>
	<body>	
		<br>
		Update client = ${idUser} with :	
		<br>
		<form action="/updated?id=${idUser}" method="get">
			<table>
				<tr>
			  	 	<td> <input hidden type="text" name="id" value="${idUser}"> </td>
				</tr>
				<tr>
			  		<td>Firstname</td>
			  	 	<td> <input type="text" name="firstname"> </td>
				</tr>
				<tr>
			  	 	<td>Lastname</td>	  	 	
				  	<td> <input type="text" name="lastname"> </td>
			 	</tr>
			  	<tr>
				  	<td>Age</td>	
				    <td> <input type="text" name="age"> </td>
				</tr>
			</table>
			<br>
			<button type="submit">Update</button>
		</form>
	</body>
</html>