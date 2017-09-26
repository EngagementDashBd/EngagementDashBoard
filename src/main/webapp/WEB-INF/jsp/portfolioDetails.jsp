<%-- <%@include file = "adminTabs.jsp"%> --%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Portfolio Details</title>


<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->

<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	 -->
<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">

<script type="text/javascript"  src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>


<!-- <link rel="stylesheet" href="css/theme/simple.css"/> -->
<link href="css/bootstrap-min-3.3.7.css" rel="stylesheet">
<link href="css/font-awesome-min4.7.0.css" rel="stylesheet">
<!-- <link href="css/jquery-dataTables-1.10.15.css" rel="stylesheet" type="text/css">
<script src="javascript/jquery.dataTables-1.10.15.js" type="text/javascript" ></script>
 -->
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
<script type="text/javascript">
function confirm_alert(node) {
    return confirm("Please click on OK to continue.");
}


</script>
<script>
$(document).ready(function(){
    $('#portfoliotable').DataTable();
});
</script>

</head>
<body>
	<div class="container" id="main">
	<center><h1><b>PORTFOLIO DETAILS</b></h1></center>
	<br>
	<br>
		<div class="row">
			<div class="col-md-12">
				<div id="empData">
				<table class="display jqueryDataTable" id="portfoliotable">
					<thead>
						<tr>
							<th>#</th>
							<th>Portfolio Name</th>
							<th>Description</th>
							<th>Update</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${portfolios}" var = "portfolio" varStatus = "loop">
					<tr>
						<th scope="row"><c:out value = "${loop.count }"></c:out></th>
						<td><c:out value = "${portfolio.portfolioName}"/></td>
						<td><c:out value = "${portfolio.portfolioDescription}"/></td>
						<td>
							<a href="fetchPortfolioDetails.htm?portfolioId=${portfolio.portfolioId}">
  								<img src="css/images/update.jpg" alt="update portfolio" style="width:30px;height:28px;border:0;">
							</a>
							</td> 
							<td>
								<a href="deletePortfolio.htm?portfolioId=${portfolio.portfolioId}" onclick="return confirm_alert(this);">
  									<img src="css/images/delete.png" alt="delete employee" style="width:30px;height:28px;border:0;">
								</a>
							</td> 
					</tr>
				</c:forEach>
												
					</tbody>
				</table>
				<a href="redirect.htm?pageName=portfolio" class="btn btn-info dropdown-toggle">Add New Portfolio</a>&nbsp;&nbsp;<a href="redirect.htm?pageName=portfolioExcel" class="btn btn-info dropdown-toggle">Portfolio Report</a>
				</div>

			</div>
		</div>
	</div>
</body>
</html>