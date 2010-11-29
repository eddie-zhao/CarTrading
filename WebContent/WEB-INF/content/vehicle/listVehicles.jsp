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
<form method="post" action="vehicle-listVehicles" id="sf">
<s:select label="Status" list="#{'ALL':'All','INIT':'Initializing','SCHEDULED':'Scheduled','BIDDING':'On Sale','FINISHED':'Finished'}" name="vehicle.status"/><br/>
<s:textfield name="vehicle.licensePlate" label="License Plate"/>
<a href="$('#sf').submit()">Search</a>
</form>
<table>
<tr style="background: gray; color: white; text-align: left;"><th></th><th>license plate</th><th>brand</th><th>status</th><th>bidding start</th><th>bidding stop</th></tr>
<s:iterator value="vehicles" status="st" var="veh">
<s:if test="#st.even"><tr style="background: silver;"></s:if>
<s:else><tr style="background: #EFEFEF;"></s:else>
<td><a href="x('vehicle-editVehicle','<s:property value="#veh.encryptedId"/>')">EDIT</a></td>
<td><s:property value="#veh.licensePlate"/></td>
<td><s:property value="#veh.brand"/></td>
<td><s:property value="#toName=:[#this=='INIT'?'Initializing':#this=='SCHEDULED'?'Scheduled':#this=='BIDDING'?'On Sale':#this=='FINISHED'?'Finished':''],#toName(#veh.status.toString())"/></td>
<td><s:property value="#veh.biddingStartOnString"/></td>
<td><s:property value="#veh.biddingStopOnString"/></td>
</tr>
</s:iterator>
</table>
</body>
</html>