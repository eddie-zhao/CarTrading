<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index of Administrator</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/x.js"></script>
</head>
<body>
Hello, <s:property value="#session.uname"/>
<a href="x('user-logout')">Exit</a><br/>
<a href="x('user-listUsers')">List Users</a>
<a href="x('vehicle-listVehicles')">List Vehicles</a>
<p/>
<form method="post" action="user-listUsers" id="sf">
<s:select list="#{'ALL':'All','CUSTOMER':'Customers','BUYER':'Traders','ADMIN':'Administrators'}" name="user.group"/>
<s:textfield name="user.name"/>
<a href="$('#sf').submit()">Search</a>
</form>
<a href="x('user-addUser')">Create New User</a>
<p>
<table>
<tr style="background: gray; color: white; text-align: left;"><th></th><th>Login-Name</th><th>User-Name</th><th>User-Type</th><th>Regist-Time</th></tr>
<s:iterator value="users" status="st" var="user">
<s:if test="#st.even"><tr style="background: silver;"></s:if>
<s:else><tr style="background: #EFEFEF;"></s:else>
<td><a href="x('user-editUser','<s:property value="#user.encryptedId"/>')">EDIT</a></td>
<td><s:property value="#user.loginName"/></td>
<td><s:property value="#user.name"/></td>
<td><s:property value="#toName=:[#this=='CUSTOMER'?'Customer':#this=='BUYER'?'Trader':#this=='ADMIN'?'Administrator':''],#toName(#user.group.toString())"/></td>
<td><s:property value="#user.regTimeString"/></td>
</tr>
</s:iterator>
</table>
</body>
</html>