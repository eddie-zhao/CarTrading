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
</body>
</html>