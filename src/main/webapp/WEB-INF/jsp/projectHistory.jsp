<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="<c:url value="/script/jquery.validate.min.js"/>"></script>
     <link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
 <!-- <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css"/>
        
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>  -->
<link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
 
 <link href="css/jquery-ui-1.7.2.css" rel="stylesheet">
<script src="javascript/jquery-ui-min-1.11.2.js" type="text/javascript" ></script>
<link href="css/bootstrap-min-3.3.7.css" rel="stylesheet">
<link href="css/font-awesome-min4.7.0.css" rel="stylesheet">
<script src="javascript/jquery-min-3.1.1.js" type="text/javascript" ></script>
<!-- <link href="css/jquery-dataTables-1.10.15.css" rel="stylesheet" type="text/css">
<script src="javascript/jquery.dataTables-1.10.15.js" type="text/javascript" ></script> -->
</head>

<body>
<script>
	$(document).ready(function() {
		$('#projTable').DataTable();
	});
</script>
<table class="display jqueryDataTable" id="projTable">
						<thead>
							<tr>
								<th>#</th>
								<th>Project Name</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Role Name</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${projects}" var="project" varStatus="loop">
					<tr>
						<td scope="row"><c:out value = "${loop.count }"></c:out></td>
						<td><c:out value = "${projectName[project.projectId]}"/></td>
						<td><c:out value = "${project.roleStartDate}"/></td>
						<td><c:out value = "${project.roleEndDate}"/></td>						
						<td><c:out value = "${project.roleName}"/></td>
					</tr>
							</c:forEach>
		
	</tbody>
	</table>
</body>
</html>