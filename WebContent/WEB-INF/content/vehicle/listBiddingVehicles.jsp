<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Management</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
function post(id) {
	$("#hiddenForm #vid").val(id).parent().submit();
}
</script>
</head>
<body>
<form id="hiddenForm" method="post" action="vehicle-viewVehicle.action">
<input type="hidden" id="vid" name="id"/>
</form>
<table>
<tr><th></th><th>license plate</th><th>brand</th></tr>
<s:iterator value="vehicles" status="st" var="veh">
<s:if test="#st.even">
<tr style="background: gray;">
</s:if>
<s:else>
<tr>
</s:else>
<td><a href="javascript:void(0)" onclick="javascript:post('<s:property value="#veh.encryptedId"/>')">view</a></td>
<td><s:property value="#veh.licensePlate"/></td>
<td><s:property value="#veh.brand"/></td>
</tr>
</s:iterator>
</table>
</body>
</html>