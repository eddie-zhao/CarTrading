<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit User</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/x.js"></script>
</head>
<body>
<a href="x('user-listUsers')">&lt;&lt; Back</a><p/>
<form method="post" action="user-updateUser" id="f">
<s:hidden name="user.encryptedId"/>
<s:label label="login name" name="user.loginName"/><br/>
<s:textfield label="user name" name="user.name"/><br/>
<s:select label="Role" list="#{'CUSTOMER':'Customer','BUYER':'Trader','ADMIN':'Administrator'}" name="user.group"/><br/>
<br/>
<a href="$('#f').submit()">Save</a>
</form>
<p/>
<a href="x('user-listUsers')">&lt;&lt; Back</a>
</body>
</html>