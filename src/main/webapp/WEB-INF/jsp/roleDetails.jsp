<%-- <%@include file = "adminTabs.jsp"%> --%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Details</title>
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <link rel="stylesheet" href="css/allPage.css">
<!-- <link rel="stylesheet" href="css/sideNav.css"> -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
 <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
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
<c:if test="${code == 'success'}">
<script>
  alert("Employee details added successfully!!");
</script>
</c:if>
<c:if test="${code == 'failure'}">
<script>
	alert("Duplicate entry for Employee Id or Enterprise Id !!!");
</script>
</c:if>
<script>
$(document).ready(function(){
    $("#employeetable").DataTable();
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
	<center><h1><b>ROLE DETAILS</b></h1></center>
	<br>
	<br>
	<br>
		<div class="row">
			<div class="col-md-12">

				<div id="empData">
					<table class="display jqueryDataTable" id="employeetable">
					<thead>
						<tr>
							<th>#</th>
							<th>Role ID</th>
							<th>Role Name</th>							
							<th>Update</th>							
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${roles}" var = "roles" varStatus = "loop">
					<tr>
						<th scope="row"><c:out value = "${loop.count }"></c:out></th>
						<td><c:out value = "${roles.id}"/></td>
						<td><c:out value = "${roles.name}"/></td>
							<td>
								<a href="fetchRoleDetails.htm?roleId=${roles.id}" >
  									<img src="css/images/update.jpg" alt="update role" style="width:30px;height:28px;border:0;">
								</a>
							</td> 							
							<td>
								<a href="deleteRoles.htm?roleId=${roles.id}" onclick="return confirm_alert(this);">
  									<img src="css/images/delete.png" alt="delete role" style="width:30px;height:28px;border:0;">
								</a>
							</td> 
					</tr>
				</c:forEach>
												
					</tbody>
				</table>
				<br>
				<a href="redirect.htm?pageName=addRole&page=role" class="btn btn-info dropdown-toggle">Add New Role</a>
				
				</div>

			</div>
		</div>
	</div>

	
</body>

</html>