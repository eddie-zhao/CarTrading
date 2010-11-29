<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create new user</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/x.js"></script>
</head>
<body>
<a href="x('user-listUsers')">&lt;&lt; Back</a><p/>
<form method="post" action="user-saveUser" id="f">
<s:textfield label="login name" name="user.loginName"/><br/>
<s:textfield label="password" name="user.password"/><br/>
<s:textfield label="user name" name="user.name"/><br/>
<s:select label="Role" list="#{'CUSTOMER':'Customer','BUYER':'Trader','ADMIN':'Administrator'}" name="user.group"/><br/>
<s:label value="car informations:"/><br/>
<s:textfield label="license plate" name="user.vehicle.licensePlate"/><br/>
<s:textfield label="brand" name="user.vehicle.brand"/><br/>
<s:select label="Status" list="#{'INIT':'Initializing','SCHEDULED':'Scheduled','BIDDING':'On sale','FINISHED':'Finished'}" name="user.vehicle.status"/><br/>
Auto scheduled is only enabled when vehicle status is <b>Scheduled</b><br/>
Time format: 2010-01-23<font color="red"><b>T</b></font>13:30:00<br/>
<s:textfield label="Start bidding on" name="scheduledStart"/>
<s:textfield label="Stop bidding on" name="scheduledStop"/>
<br/>
<a href="$('#f').submit()">Save</a>
</form>
<p/><a href="x('user-listUsers')">&lt;&lt; Back</a>
</body>
</html>