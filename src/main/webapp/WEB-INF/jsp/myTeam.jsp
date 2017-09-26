<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- <%@include file = "employeeTabs.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Team</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="css/allPage.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
	<script src="script/profile.js"></script>
<script 
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
 <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
</head>
<body>
<script>
$(document).ready(function(){
    $('#teamtable').DataTable();
}); 
</script>
 <c:if test="${code == 'success'}">
<script>
  alert("Updated Successfully.....");
</script>
</c:if>
<c:if test="${code == 'failure'}">
<script>
  alert("Update Failure.... Please Try again !!!");
</script>
</c:if>
 <c:if test="${hubcode == 'success'}">
<script>
  alert("Employee Added To Hub.....");
</script>
</c:if>
<c:if test="${hubcode == 'failure'}">
<script>
  alert("Error!!!.... Please Try again !!!");
</script>
</c:if>
<script>
 $(document).ready(function(){
$('.recommendToHold').click(function() {
	if ($(this).is(':checked'))
		{		
		document.getElementById('potentialFutureRole').style.visibility = 'visible'; 
		document.getElementById('hold').style.visibility = 'visible'; 
		$('#employeeId').val($(this).val());
		}
	
  	
});
}); 

</script>
	<div class="container" id="main">
	
	<div class="row">
			<div class="col-md-12">
				<br>
				<br>
				
				<c:if test="${roleoffdetails != 'yes'}">
					<a href="employeesRoleOff.htm" class="btn btn-info dropdown-toggle">Role Off Details</a>
				</c:if>
				<c:if test="${roleoffdetails == 'yes'}">
					<a href="employeesUnderSupervisor.htm" class="btn btn-info dropdown-toggle">My Team</a>
				</c:if>
				<br>
				<br>
				<div id="empData">
					<form action="recommendToHold.htm">
					<table class="display jqueryDataTable" id="teamtable">
					<thead>
						<tr>
							<th>#</th>
							<th>Enterprise ID</th>
							<th>Employee Name</th>
							<th>Designation</th>
							<th>Employee's Project Name</th>
							<th>Roll Start Date</th> 
							<th>Roll End Date</th> 
							
							<sec:authorize access="hasRole('LCR_VIEW')">
								<th>LCR</th>
							</sec:authorize>
							 <c:if test="${roleoffdetails == 'yes'}">
							 	<th>15 days Holding Cost</th>
							 	<th>45 days Holding Cost</th>
							 	<th>Recommend To Hold</th>
							 </c:if>
							
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${employees}" var = "employees" varStatus = "loop">
					<tr>
						<th scope="row"><c:out value = "${loop.count }"></c:out></th>
						<td><c:out value = "${employees.enterpriseId}"/></td>
						<td><c:out value = "${employees.employeeName}"/></td>
						<td><c:out value = "${employees.careerLevel.designation}"/></td>
						<td><c:out value = "${employees.project.projectName}"/></td>
						<td><fmt:formatDate value="${employeeAndProject[employees.employeeId].roleStartDate}" pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${employeeAndProject[employees.employeeId].roleEndDate}" pattern="yyyy-MM-dd" /></td>
						<sec:authorize access="hasRole('LCR_VIEW')">
							<td><c:out value = "${employeeAndProject[employees.employeeId].lcr}"/></td>
						</sec:authorize>
						<%-- <td>
								<a href="fetchDetails.htm?enterpriseid=${employees.enterpriseId}">
  									<img src="css/images/update.jpg" alt="update employee" style="width:30px;height:28px;border:0;">
								</a>
							</td>  --%>
						 <c:if test="${roleoffdetails == 'yes'}">		
						 	<td><c:out value = "${employeeAndProject[employees.employeeId].lcr * 9 * 15}"/></td>	
						 	<td><c:out value = "${employeeAndProject[employees.employeeId].lcr * 9 * 45}"/></td>
						 	<td><input type="checkbox" name="${employees.employeeId}" value="${employees.employeeId}" class="recommendToHold" /></td>
						</c:if>
						
					</tr>
				</c:forEach>
												
				<!-- <tr>	<td><input type="hidden" id="employeeId" name="employeeId" /></td></tr>
					<tr><td><input type="text" id="potentialFutureRole" name="potentialFutureRole"  placeholder="Future Potential Role" style="visibility:hidden"/>	</td>
				<td><input class="btn btn-info dropdown-toggle" type="submit" value="Add Details"  id="hold" style="visibility:hidden"/></td></tr>
		 -->			</tbody>			 	 
				</table>
				<input type="hidden" id="employeeId" name="employeeId" />
					<input type="text" id="potentialFutureRole" name="potentialFutureRole"  placeholder="Future Potential Role" style="visibility:hidden"/>	
				<input class="btn btn-info dropdown-toggle" type="submit" value="Hold"  id="hold" style="visibility:hidden"/>
		
		</form>
				</div>
				<br>
				<br> 
			</div>
		</div>
	</div>
</body>
</html>