<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
td {
    height: 50px;
    width: 180px;
}

</style>
</head>
<body>
<script>
	$(document).ready(function() {
		$('#devTable').DataTable();
	});
</script>
<div id="ideaDetails">
	<table>
		<tr><td><label>Idea Description:</label></td><td><c:out value="${idea.ideaDescription}"></c:out></td><td><label>Status:</label></td><td><c:out value="${ideaStatus}"></c:out></td></tr>
		<tr><td><label>Category:</label></td><td><c:out value="${idea.category}"></c:out></td><td><label>Sub Category:</label></td><td><c:out value="${idea.subCategory}"></c:out></td></tr>
		<tr><td><label>Proposed By:</label></td><td><c:out value="${idea.enterpriseId}"></c:out></td><td><%-- <label>Approved By:</label></td><td><c:out value="${ideaStatus.enterpriseId}"></c:out> --%></td></tr>
	</table>
	<hr>
<div id="developers">
<h1>Developers</h1>
	<table class="display jqueryDataTable" id="devTable">
						<thead>
							<tr>
								<th>#</th>
								<th>Name</th>
								<th>Employee Id</th>
								<th>Enterprise Id</th>
								<th>Designation</th>
								<th>Project</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${developerDetails}" var="developers" varStatus="loop">
								<tr>
						<th scope="row"><c:out value = "${loop.count }"></c:out></th>
						<td><c:out value = "${developers.employeeName}"/></td>
						<td><c:out value = "${developers.employeeId}"/></td>
						<td><c:out value = "${developers.enterpriseId}"/></td>						
						<td><c:out value = "${developers.careerLevel.designation}"/></td>
						<td><c:out value="${developers.project.projectName}"></c:out></td>						
					</tr>
							</c:forEach>
		
	</tbody>
	</table>
</div>
</div>
</body>
</html>