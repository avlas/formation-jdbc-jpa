<html>
	<br>
	Update client = ${idUser} with :	
	
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
			
		<button type="submit">Update</button>
	</form>
</html>