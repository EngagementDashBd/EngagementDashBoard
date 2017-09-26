<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Career Level</title>
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
<c:if test="${code == 'success'}">
<script>
  alert("Updated Successfully");
</script>
</c:if>
<c:if test="${code == 'failure'}">
<script>
  alert("Error Updating the data");
</script>
</c:if>
<script>
$(document).ready(function(){
    $("#careertable").DataTable();
});
</script>
</head>
<body>
<div class="container" id="main">
	<center><h1><b>EMPLOYEE DETAILS</b></h1></center>
	<br>
	<br>
	<br>
		<div class="row">
			<div class="col-md-12">

				<div id="careerLevel">
					<table class="display jqueryDataTable" id="careertable">
					<thead>
						<tr>
							<th>#</th>
							<th>Career Level</th>
							<th>Designation</th>
							<th>Shift B Amount</th>
							<th>Shift C Amount</th>
							<th>Update</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${levels}" var = "levels" varStatus = "loop">
					<tr>
						<th scope="row"><c:out value = "${loop.count }"></c:out></th>
						<td><c:out value = "${levels.level}"/></td>
						<td><c:out value = "${levels.designation}"/></td>
						<td><c:out value = "${levels.shiftbAmount}"/></td>					
						<td><c:out value = "${levels.shiftcAmount}"/></td>						
						<td>
								<a href="fetchLevelDetails.htm?level=${levels.level}" >
  									<img src="css/images/update.jpg" alt="update employee" style="width:30px;height:28px;border:0;">
								</a>
							</td> 
							
							
					</tr>
				</c:forEach>
												
					</tbody>
				</table>
				<br>
				<!-- <a href="redirect.htm?pageName=adminPanel" class="btn btn-info dropdown-toggle">Add New Level</a> -->				
				</div>

			</div>
		</div>
	</div>

</body>
</html>