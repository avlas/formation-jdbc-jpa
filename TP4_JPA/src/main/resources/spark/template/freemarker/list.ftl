<html>
	<head>
			<style>
				table {
				    font-family: arial, sans-serif;
				    border-collapse: collapse;
				    width: 100%;
				}
				
				td, th {
				    border: 1px solid #dddddd;
				    text-align: left;
				    padding: 8px;
				}				
			</style>
	</head>
	<body>	
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
	</body>
</html>