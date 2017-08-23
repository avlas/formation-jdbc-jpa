<html>
Bonjour ! 
<br>
<br>
<table>
  <tr>
    <th>Name</th>
  </tr>
  <#list objets as obj>
	  <tr>
	    <td>${obj.nom}</td>
	  </tr>
  </#list>
</table>
</html>