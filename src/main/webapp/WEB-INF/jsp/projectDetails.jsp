<%-- <%@include file = "adminTabs.jsp"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Details</title>
<%-- <script type="text/javascript" src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script> --%>
<script type="text/javascript" src="<c:url value="/script/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/admin/projectdetails.js"/>"></script>

<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
 <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>

<link href="css/bootstrap-min-3.3.7.css" rel="stylesheet">
<link href="css/font-awesome-min4.7.0.css" rel="stylesheet">
<!-- <link href="css/jquery-dataTables-1.10.15.css" rel="stylesheet" type="text/css">
<script src="javascript/jquery.dataTables-1.10.15.js" type="text/javascript" charset="utf8" ></script> -->

<c:if test="${deletecode == 'success'}">
<script>
  alert("Deleted Successfully");
</script>
</c:if>
<c:if test="${deletecode == 'failure'}">
<script>
  alert("Error Deleting the data");
</script>
</c:if>

<c:if test="${updatecode == 'success'}">
<script>
  alert("Updated Successfully");
</script>
</c:if>
<c:if test="${updatecode == 'failure'}">
<script>
  alert("Error Updating the data");
</script>
</c:if>

<script>
$(document).ready(function(){
    $('#projecttable').DataTable();
});
</script>

<script type="text/javascript">
function confirm_alert(node) {
    return confirm("Please click on OK to continue.");
}
</script>
</head>

<body>
<div class="container" id="main">
<center><h1><b>PROJECT DETAILS</b></h1></center>
	<br>
	<br>
		<div class="row" align="center">
			<div class="col-md-12">
<div id="proData" align="center">
					<table class="display jqueryDataTable" id="projecttable">
					<thead>
						<tr>
							<th>#</th>
							
							<th>Project Name</th>
							<th>ProjectDescription</th>
							<th>program Name</th>
							<th>Update</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${projects}" var = "allprojectDetailsData" varStatus = "loop">
					<tr>
						<th scope="row"><c:out value = "${loop.count }"></c:out></th>
						<td><c:out value = "${allprojectDetailsData.projectName}"/></td>
						<td><c:out value = "${allprojectDetailsData.projectDescription}"/></td>
					<%-- 	<td><c:out value = "${allprojectDetailsData. programId}"/></td> --%>
						 <!--  <td><i class="fa fa-pencil fa-2x" aria-hidden="true"></i></td>-->
						<!--  <td><i class="fa fa-pencil fa-2x" aria-hidden="true"></i></td>-->
							<td><c:out value = "${allprojectDetailsData.program.programName}"/></td> 
							<td>
								<a href="fetchProjectDetails.htm?id=${allprojectDetailsData.id}" >
  									<img src="css/images/update.jpg" alt="update Project" style="width:30px;height:28px;border:0;">
								</a>
							</td> 
							
							<td>
								<a href="deleteProject.htm?id=${allprojectDetailsData.id}">
  									<img src="css/images/delete.png" alt="delete Project" style="width:42px;height:32px;border:0;">
								</a>
							</td> 
					</tr>
				</c:forEach>
												
					</tbody>
				</table>
				<a href="getAllPrograms.htm" class="btn btn-info dropdown-toggle">Add New Project</a>&nbsp;&nbsp;<a href="redirect.htm?pageName=projectExcel" class="btn btn-info dropdown-toggle">Project Report</a>
				
				</div>
				</div>
				</div>
				</div>
				



</body>
</html>