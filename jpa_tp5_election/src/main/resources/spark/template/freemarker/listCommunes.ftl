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
				
			 <#list communes as commune>
				  <tr>
				    	<td>${commune.id}</td>
					    <td>${commune.nom}</td>
					  	<td>${commune.maire}</td>
				  </tr>
			  </#list>
		</table>
	
		<br> 
		<form action="/addClient" method="post">
			<button type="submit">Add client</button>
		</form>
	</body>
</html>