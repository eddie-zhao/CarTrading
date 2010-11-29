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
<a href="x('vehicle-listVehicles')">&lt;&lt; Back</a><p/>
<form method="post" action="vehicle-updateVehicle" id="f">
<s:hidden name="vehicle.encryptedId"/>
<s:textfield label="license plate" name="vehicle.licensePlate"/><br/>
<s:textfield label="brand" name="vehicle.brand"/><br/>
<s:select label="Status" list="#{'INIT':'Initializing','SCHEDULED':'Scheduled','BIDDING':'On sale','FINISHED':'Finished'}" name="vehicle.status"/><br/>
Auto scheduled is only enabled when vehicle status is <b>Scheduled</b><br/>
Time format: <font color="blue">2010-01-23 13:30:00</font><br/>
<s:textfield label="Start bidding on" name="vehicle.biddingStartOnString"/>
<s:textfield label="Stop bidding on" name="vehicle.biddingStopOnString"/>
<br/>
<a href="$('#f').submit()">Save</a>
</form>
<p/>
<a href="x('vehicle-listVehicles')">&lt;&lt; Back</a>
</body>
</html>