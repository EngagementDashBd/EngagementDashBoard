
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/dataTables.min.css">


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>






<link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>



<script>
	$(document).ready(function() {
		$('#WsrRecords').DataTable();
	});
</script>
<script>
	function getWSRbyProjectID() {
		//alert("this is n=inside function1");
		$('#reportcontent').load('addResourceTaskwithoutajax.htm');

	}
</script>

<script>
	$(document).ready(function() {
		$.ajaxSetup({
			cache : false
		});
	});
</script>
<script>
	function getWSRDetailDialog(value) {

		/* alert(document.getElementById(value).text) */
		//document.getElementById("name").text=null;
		$('#resourcecontent').load('getResourceVacationDetails.htm', {
			PROID : document.getElementById(value).name
		}, function() {

			$("").dialog({

				maxWidth : 1200,
				maxHeight : 1500,
				width : 850,
				height : 550,
				modal : false,
				position : {
					my : "top",
					at : "bottom",
					of : "#header"
				},
				title : 'Weekly Status Report',
				buttons : {

					"Cancel": function () {
						 $(this).dialog('destroy').close();
					}
				}

			});
			}

		);

	}

	/* $("#resourcecontent").position({
	 my: "center",
	 at: "center",
	 of: window
	 });
	 */
</script>

</head>
<body>

	<table id=WsrRecords class="stripe">
		<thead>
			<tr>
				<th>Work ID</th>
				<th>Project Name</th>
				<th>Release Name</th>
				<th>Release Live Date</th>
				<th>Start Date</th>
				<th>End Date</th>

			</tr>
		</thead>

		<c:forEach items="${recordsByProjectName}" var="recordsByProjectName"
			varStatus="loop">
			<tr>
				<td><a onclick="return  getWSRDetailDialog(this.id);"
					name="${recordsByProjectName.workId}" id="name${loop.index}"
					value="${recordsByProjectName.workId}"><c:out
							value="${recordsByProjectName.workId}" /> </a></td>
				<td><c:out value="${recordsByProjectName.applicationName}" /></td>
				<td><c:out value="${recordsByProjectName.releaseName}" /></td>
				<td><c:out value="${recordsByProjectName.releaseDate}" /></td>
				<td><c:out value="${recordsByProjectName.weekStart}" /></td>
				<td><c:out value="${recordsByProjectName.weekend}" /></td>

			</tr>
		</c:forEach>

	</table>

<div id="resourcecontent"></div>
	
</body>
</html>