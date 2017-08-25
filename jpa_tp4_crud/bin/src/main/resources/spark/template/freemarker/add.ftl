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
		Add a client :	
		<br>
		<form action="/add" method="post">
			<table>
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
			<button type="submit">Add client</button>
		</form>
	</body>
</html>