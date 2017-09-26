<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- <c:if test = "${resource.employeeName == 'Administrator'}">
<link rel="stylesheet" href="css/sideNav.css">
<%@include file = "adminTabs.jsp"%>
</c:if>
<c:if test = "${resource.employeeName != 'Administrator'}">
<%@include file = "employeeTabs.jsp"%>
</c:if> --%>
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
<c:if test="${holdcode == 'success'}">
<script>
  alert("Employee Held successfully!!");
</script>
</c:if>
<c:if test="${holdcode == 'failure'}">
<script>
  alert("Error.. Please Try Again!!");
</script>
</c:if>
<c:if test="${roleoffcode == 'success'}">
<script>
  alert("Employee's role off details updated successfully!!");
</script>
</c:if>
<c:if test="${roleoffcode == 'failure'}">
<script>
	alert("Error.. Please Try Again!!");
</script>
</c:if>
<script>
$(document).ready(function(){
    $('#hubtable').DataTable();
}); 
</script>
<script>
$(document).ready(function(){
	$('select').change(function () {
		 var optionSelected = $(this).find("option:selected");
		 var valueSelected  = optionSelected.val();
	     var textSelected   = optionSelected.text();
	     alert(valueSelected);
	     alert(textSelected);
	     if(textSelected == 'Y')
	   	 {
	    	 document.getElementById('potentialFutureRole').style.visibility = 'visible';
	    	 document.getElementById('roleOffDate').style.visibility = 'hidden';
	    	 document.getElementById('hold').style.visibility = 'visible'; 
	    	 document.getElementById('roleOff').style.visibility = 'hidden'; 
	 		 $('#employeeId').val($(this).val());
	     }
	    	 
	     if(textSelected == 'N')
	     {
	    	 document.getElementById('roleOffDate').style.visibility = 'visible';
	    	 document.getElementById('potentialFutureRole').style.visibility = 'hidden';
	    	 document.getElementById('hold').style.visibility = 'hidden'; 
	    	 document.getElementById('roleOff').style.visibility = 'visible';
	 		 $('#employeeId').val($(this).val());
	     }
	     if(textSelected == '--select--')
	     {
	    	 document.getElementById('roleOffDate').style.visibility = 'hidden';
	    	 document.getElementById('potentialFutureRole').style.visibility = 'hidden';
	    	 document.getElementById('hold').style.visibility = 'hidden'; 
	    	 document.getElementById('roleOff').style.visibility = 'hidden';
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
				<center><h1>Employees On Hub</h1></center>
				<br>
				<br>
				<form action="holdOrRoleOff.htm">
				<div id="empData">
				<table class="display jqueryDataTable" id="hubtable">
					<thead>
						<tr>
							<th>#</th>
							<th>Enterprise ID</th>
							<th>Employee Name</th>							
							<th>Hub Start Date</th> 
							 <th>Potential Future Role</th> 
							 <sec:authorize access="hasRole('LCR_VIEW')">
								<th>LCR</th>
							</sec:authorize>
							<th>15 days Holding Cost</th>
							<th>45 days Holding Cost</th>
							<!-- <th>Employee Status</th> -->
							<th>Recommend to hold</th>
						<%-- 	<th>Update</th>
							 <c:if test="${roleoffdetails == 'yes'}">
							 	<th>Recommend To Hold</th>
							 </c:if>  --%>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${employees}" var = "employees" varStatus = "loop">
					<tr>
						<th scope="row"><c:out value = "${loop.count }"></c:out></th>
						<td><c:out value = "${employees.enterpriseId}"/></td>
						<td><c:out value = "${employees.employeeName}"/></td>	
						<td><fmt:formatDate value="${employeeAndHub[employees.employeeId].hubStartDate}" pattern="yyyy-MM-dd" /></td>				
						<%-- <td><c:out value = "${employeeAndHub[employees.employeeId].hubStartDate}"/></td> --%>
						<td><c:out value = "${employeeAndHub[employees.employeeId].potentialFutureRole}"/></td>
						<sec:authorize access="hasRole('LCR_VIEW')">
							<td><c:out value = "${employeeAndProject[employees.employeeId].lcr}"/></td>
						</sec:authorize>
						<td><c:out value = "${employeeAndProject[employees.employeeId].lcr * 9 * 15}"/></td>	
						<td><c:out value = "${employeeAndProject[employees.employeeId].lcr * 9 * 45}"/></td>	
					<%-- 	<td><c:out value = "${employees.employeeStatus}"/></td> --%>
						<td><select name = "recommendToHold" id="recommendToHold">
								<option value="none">--select--</option>
								<c:if test="${employeeAndProject[employees.employeeId].recommendToHold == false and employees.roleOffDate != null}">
									<option value="${employees.employeeId}">Y</option>
									<option value="${employees.employeeId}" selected>N</option>	
								</c:if>
								<c:if test="${employeeAndProject[employees.employeeId].recommendToHold == true and employees.roleOffDate == null}">
									<option value="${employees.employeeId}" selected>Y</option>
									<option value="${employees.employeeId}">N</option>	
								</c:if>		
								<c:if test="${employeeAndProject[employees.employeeId].recommendToHold == false and employees.roleOffDate == null}">
									<option value="${employees.employeeId}">Y</option>
									<option value="${employees.employeeId}">N</option>	
								</c:if>					
							</select>
						</td>			  
						
						
						
						
					</tr>
				</c:forEach>
												
				
					</tbody>			 	 
				</table>
				<input type="hidden" id="employeeId" name="employeeId" />
				<input type="text" id="potentialFutureRole" name="potentialFutureRole"  placeholder="Future Potential Role" style="visibility:hidden"/>
				<input type="text" id="roleOffDate" name="roleOffDate"  placeholder="Role Off Date" style="visibility:hidden"/>	
				<input class="btn btn-info dropdown-toggle" type="submit" value="Hold"  id="hold" name="holdorroleoff" style="visibility:hidden"/>
				<input class="btn btn-info dropdown-toggle" type="submit" value="RoleOff"  id="roleOff" name="holdorroleoff" style="visibility:hidden"/>
				</form>
				<a href="redirect.htm?pageName=employeeHubExcel" class="btn btn-info dropdown-toggle">Hub Report</a>
				</div>
				<br>
				<br> 
			</div>
		</div>
	</div>
</body>
</html>