<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Learning Dashboard</title>
<link rel="stylesheet" href="css/learningDashboard.css" media="all">
<script>
	function goBack() {
		window.location = "hcscMenu.htm";
	}
</script>
</head>
<body>
	<div class="header">
		<hr>
		<button type="button" onClick="goBack()" style="cursor: pointer;">Back</button>
		<center>
			<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HCSC Learning Dashboard</h1>
		</center>
		<hr>
	</div>
	<br>
	<div class="details">
		<br> <b>Enterprise Id: &nbsp;&nbsp;&nbsp;&nbsp;</b>
		<c:out value="${enterpriseId}"></c:out>
		<br> <br> <b>Employee Name: </b>
		<c:out value="${employeeName}"></c:out>
		<br> <br> <b>Employee Id:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
		<c:out value="${employeeId}"></c:out>
		<br> <br>
	</div>
	<br>
	<br>
	<table id="example" class="display" cellspacing="0" width="70%"
		border="1" align="center">
		<thead>
			<tr>
				<th>Course Name</th>
				<th>Status</th>
				<th>Completion Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${learningData}" var="employees" varStatus="loop">
				<tr>
					<td><c:choose>
							<c:when test="${employees.topicId == '1'}">Business Continuity Plan</c:when>
							<c:when test="${employees.topicId == '2'}">Navriti tool and ideas</c:when>
							<c:when test="${employees.topicId == '3'}">Overview of HCSC</c:when>
							<c:when test="${employees.topicId == '4'}">Overview of HealthCare in US</c:when>
							<c:when test="${employees.topicId == '5'}">Client data protection & Information security</c:when>
							<c:when test="${employees.topicId == '6'}">Organization chart in HCSC</c:when>
							<c:when test="${employees.topicId == '7'}">Multi-skilling & Technology stack</c:when>
							<c:when test="${employees.topicId == '8'}">HealthCare overview in US - Day 1</c:when>
							<c:when test="${employees.topicId == '9'}">HealthCare overview in US - Day 2</c:when>
							<c:when test="${employees.topicId == '10'}">HealthCare overview in US - Day 3</c:when>
							<c:when test="${employees.topicId == '11'}">HealthCare overview in US - Day 4</c:when>
							<c:when test="${employees.topicId == '12'}">HealthCare overview in US - Day 5</c:when>
							<c:when test="${employees.topicId == '13'}">HealthCare overview in US - Day 6</c:when>
							<c:when test="${employees.topicId == '14'}">Navriti Framework</c:when>
							<c:when test="${employees.topicId == '15'}">Tool Assets in HCSC</c:when>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${employees.completionStatus == 'true'}">Completed</c:when>
							<c:when test="${employees.slideNumber != '1'}">In Progress</c:when>
							<c:otherwise>Yet to Start</c:otherwise>
						</c:choose></td>
					<td><fmt:formatDate value="${employees.completionDate}"
							pattern="dd-MM-yyyy" /></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>Course Name</th>
				<th>Status</th>
				<th>Completion Date</th>
			</tr>
		</tfoot>
	</table>
	<div class="footer" align="center">
		<a href="https://accenture.com">@ Copyright 2017-2018 Accenture.
			All Rights Reserved. Accenture Confidential. For Internal Use only</a>
	</div>
</body>
</html>