<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--  <link rel="stylesheet" type="text/css" href="css/weekly.css">  -->

  
<link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>


	
<script>
	$(document).ready(function() {
		$('#WSRRecords').DataTable();
	});
</script>
<script>
	$(function() {
		$(".datepicker").datepicker();
		false;
	});
</script>


<style>
table.statusTable {
    border-collapse: inherit;
    width: 85%;
     border: 1px solid #ddd;
       padding: 15px;
       border-color:  black;
       border-radius: 10px;
       color: gray;
       
}
td{
font-size:100%;
font-family: Verdana;
padding: 10px;
}
</style>

</head>
<body>
<table class="statusTable" align="center">
<%-- <center><h2>Weekly Status Details   </h2></center> --%>
<h3>Weekly Status Details</h3>
<br>
<br>
<br>
<h4>Release Details </h4>

<tr><td> Work ID:</td><td><c:out value = "${wrsRecordsByWorkID.workId}"/></td>
<td> Project Name:</td><td><c:out value = "${wrsRecordsByWorkID.applicationName}"/></td></tr>
<tr><td>Release Name:</td><td><c:out value = "${wrsRecordsByWorkID.releaseName}"/></td>
<td>Release Live Date:</td><td><c:out value = "${wrsRecordsByWorkID.releaseDate}"/></td></tr>
<tr><td>Start Date:</td><td><c:out value = "${wrsRecordsByWorkID.weekStart}"/></td>
<td>End Date:</td><td><c:out value = "${wrsRecordsByWorkID.weekend}"/></td></tr>
<tr><td>Project Accomplishments: </td><td><c:out value = "${wrsRecordsByWorkID.projectAccomplishment}"/></td></tr>
<tr><td>Impediments</td><td><c:out value = "${wrsRecordsByWorkID.risk}"/></td></tr>

</table>
<br>
<br>
<br>
<br>
<br>
<h4>Resource Task and Vacation Details</h4>
	<table id=WSRRecords>
		<thead>
			<tr>
				<th> Resource Name</th>
				<th>Task Update</th>
				<th>Vacation From </th>
				<th>Vacation To</th>
				
			
			</tr>
		</thead>
	
		<c:forEach items= "${recordsByWorkID}"  var = "recordsByWorkID" varStatus = "loop">
		<tr>
		
		<td><c:out value = "${recordsByWorkID.resourceName}"/> </td>
		<td><c:out value = "${recordsByWorkID.taskUpdate}"/> </td>
		<td><c:out value = "${recordsByWorkID.vocationDateTo}"/> </td>
		<td><c:out value = "${recordsByWorkID.vocationDateFrom}"/> </td>

		</tr>
		</c:forEach>
	
	</table>

</body>
</html>