<%-- <%@include file = "adminTabs.jsp"%> --%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Details</title>
<!-- <link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
 <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
	<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
 <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="script/employeedetails.js"></script> 
  <link rel="stylesheet" href="/resources/demos/style.css">
  <link href="css/font-awesome-min4.7.0.css" rel="stylesheet">
  <script src="javascript/bootstrap-min-3.3.7.js" type="text/javascript"></script>
  <!-- <link href="css/jquery-dataTables-1.10.15.css" rel="stylesheet" type="text/css">
<script src="javascript/jquery.dataTables-1.10.15.js" type="text/javascript" ></script> -->
  

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
<c:if test="${code == 'success'}">
<script>
  alert("Added Successfully");
</script>
</c:if>
<c:if test="${code == 'failure'}">
<script>
  alert("Error Adding the data");
</script>
</c:if>
<script type="text/javascript">
function confirm_alert(node) {
    return confirm("Please click on OK to continue.");
}
</script>

<script>
$(document).ready(function(){
    $('#techtable').DataTable();
});
</script>

 </head>
<body>
	
	<div class="container" id="main">
	<center><h1><b>TECHNOLOGY DETAILS</b></h1></center>
		<div class="row">
			<div class="col-md-12">
                <br>
                <br>
               
				<div id="empData">
					<table class="display jqueryDataTable" id="techtable">
					<thead>
						<tr>
							<th>#</th>
							<th>Technology</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${technologies}" var = "technology" varStatus = "loop">
					<tr>
						<th scope="row"><c:out value = "${loop.count }"></c:out></th>
						<td><c:out value = "${technology.technologyName}"/></td>
						<td>
								<a href="deleteTechnology.htm?technologyid=${technology.technologyId}" onclick="return confirm_alert(this);">
  									<img src="css/images/delete.png" alt="delete technology" style="width:30px;height:28px;border:0;">
								</a>
						</td> 
					</tr>
					
				</c:forEach>
															
					</tbody>
				</table>
				<a href="redirect.htm?pageName=addTechnology" class="btn btn-info dropdown-toggle">Add New Technology</a>&nbsp;&nbsp;<a href="redirect.htm?pageName=technologyExcel" class="btn btn-info dropdown-toggle">Technology Report</a>
				</div>

			</div>
		</div>
	</div>

	
</body>

</html>