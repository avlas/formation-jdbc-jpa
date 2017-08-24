<html>
	<table>
		  <tr>
		   		<th>Id</th>
			    <th>Firstname</th>
			    <th>Lastname</th>
			    <th>Age</th>
		  </tr>
			
		 <#list clients as client>
			  <tr>
			    	<td>${client.id}</td>
				    <td>${client.firstname}</td>
				  	<td>${client.lastname}</td>
				    <td>${client.age}</td>
				    <td> <a href="update?id=${client.id}"> Update </a> </td> 
				    <td> <a href="delete?id=${client.id}"> Delete </a> </td> 
			  </tr>
		  </#list>
	</table>

	<br> 
	<form action="/addClient" method="post">
		<button type="submit">Add client</button>
	</form>

</html>