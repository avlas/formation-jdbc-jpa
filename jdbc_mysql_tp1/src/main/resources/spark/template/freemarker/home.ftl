<html>
<br>
<br>
<table>
  <tr>
    <th>Name</th>
    <th>Tarif</th> 
  </tr>
  <#list plats as plat>
	  <tr>
	    <td>${plat.name}</td>
	    <td>${plat.tarif}</td> 
	  </tr>
  </#list>
</table>
</html>