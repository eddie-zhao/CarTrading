<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/x.js"></script>
</head>
<body>
<s:if test="#session.uid==null">
<form method="post" action="user-login" id="f">
name:<input type="text" name="user.loginName"/>
password:<input type="password" name="user.password"/>
<a href="$('#f').submit()">Login</a>
</form>
</s:if>
<s:else>
Hello, <s:property value="#session.uname"/>
<br/><a href="x('user-logout')">Logout</a>
<br/><a href="x('user-listUsers')">List Users</a>
<br/><a href="x('vehicle-listVehicles')">List Vehicles</a>
<br/><a href="x('vehicle-listBiddingVehicles')">List Bidding Vehicles</a>
</s:else>
</body>
</html>