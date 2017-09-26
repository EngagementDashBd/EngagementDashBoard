<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/themes/south-street/jquery-ui.css"> 
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script> 
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/jquery.validate.min.js"/>"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/weekly.css">

<link rel="stylesheet" type="text/css" href="css/dataTables.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="myscripts.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">

<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
	
	<link rel="stylesheet" type="text/css"	href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>

<script>
	
	/* var hiddenInput = document.getElement("input");
	$(hiddenInput).attr({
	    'id':'uniqueIdentifier',
	    'type': 'hidden',
	    'value': ID,
	    'class': 'txtStartDate'
	});

	var hiddenInput = document.getElement("input");
	$(hiddenInput).attr({
	    'id':'uniqueIdentifier',
	    'type': 'hidden',
	    'value': ID,
	    'class': 'txtEndDate'
	});
	 */
	function date(i){
			
		$(function() {
			
			var start = "#start"+i;
			var end = "#end"+i;
			var dateFormat = "dd-mm-yy",
			from = $(start)

			.datepicker({
				changeMonth : true,
				numberOfMonths : 1, 
				dateFormat: 'dd-mm-yy'
			})
			.on("change", function() {
				to.datepicker("option", "minDate", getDate(this));
			}),

			to = $(end).datepicker({
				changeMonth : true,
				numberOfMonths : 1, 
				dateFormat: 'dd-mm-yy'
			})
			.on("change", function() {
				from.datepicker("option", "maxDate", getDate(this));
			});

			function getDate(element) {
				var date;
				try {
					date = $.datepicker.parseDate(dateFormat, element.value);
				} catch (error) {
					date = null;
				}
				return date;
			}

		});
	 }
</script>
<style>

 input[type='text'], input[type='password']
{

transition: 200ms all ease;
width: 215px;
height: 32px;
border-radius: 3px;
border: 0.8px solid #CCC;
padding: 12px;
font-weight: 200;
font-size: 14px;
font-family: Verdana;
box-shadow: 1px 1px 25px #333;
}
#tittle{
background: navy;
}

input[type='text']:hover, input[type='password']:hover
{
width: 220px;
height: 33px;
border-radius: 3px;
border: 0.9px solid #aaa;
padding: 12px; 
 border-color: #00BFFF #00BFFF #00BFFF #00BFFF;
transition: 200ms all ease;
font-weight: 200;
font-size: 15px;
font-family: Verdana;
box-shadow: 1px 1px 18px #CCC;
} 
textarea
{
width: 350px;
resize:vertical;
height: 69px;
border-radius: 3px;
border: 1px solid #CCC;
padding: 8px;
font-weight: 200;
font-size: 15px;
 font-family:  sans-serif;
box-shadow: 1px 1px 10px #333;

}
textarea:hover
{
width: 351px;
 resize:vertical;
height: 69px;
border-radius: 3px;
border: 1px solid #aaa;
border-color: #00BFFF #00BFFF #00BFFF #00BFFF;
padding: 8px;
font-weight: 200;
font-size: 15px;
font-family: Verdana;
box-shadow: 1px 1px 5px #CCC;
}
table.resTable {
  border-collapse: separate;
 
  border-spacing: 20px 0px;
    width: 65%;
   
    border-spacing: 12px  5px;
     table-layout: auto;
    	
}
td.resTable {
    width: 1px;
    white-space: nowrap;
    position: inherit;
    
}
th.resTable
{
color:#000000;	
}
tr.resTable{
color:#808080;
}
</style> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ResourceView</title>
</head>
<body>
<script>
function delete1()
{
	alert('asfd');
$('.resTable').on("click", "button", function(){
  console.log($(this).parent());
  table.row($(this).parents('tr')).remove().draw(false);
});
}
</script>

<div id="form" >
<h3><strong>Resources in  Release and  their Vacation Details  </strong></h3>
<form:form method="POST" action="addResourceTask.htm"  id="addResource">
<table class="resTable" frame="box">


					<tr class="break">
						<td colspan="2"></td>
					</tr>
					
					<tr>
						<th rowspan="2" >Resource Name</th>
						<th rowspan="2" style="text-align: center; ">Task Updates</th>
						<th colspan="2" style="text-align: center; ">Vacation
							Dates</th>
					</tr>
					<tr>
						<th style="text-align: center; "><i>From</i></th>
						<th style="text-align: center; "><i>To</i></th>
					</tr>
					<tr class="break">
						<td colspan="2"></td>
					</tr>
					<c:forEach items="${resourcesInProject}" var="resource" varStatus="status">

						<tr>

							<td style="color:#808080;"><label
								for="resourceTaskList[${status.index}].resourceName">${resource.employeeName}</label><input
								type="hidden"
								name="resourceTaskList[${status.index}].resourceName"
								value="${resource.employeeName}"></td>
							

					<td><textarea rows="10" cols="80"
									name="resourceTaskList[${status.index}].taskUpdate"></textarea></td>
					<td><input type="text"
								name="resourceTaskList[${status.index}].vocationDateTo"
								class="txtStartDate" id = "start${status.index}" onmouseover="date(${status.index})"/></td>
						<td><input type="text"
								name="resourceTaskList[${status.index}].vocationDateFrom"
								class="txtEndDate" id = "end${status.index}" onmouseover="date(${status.index})"/></td>
								
						</tr>
						<tr class="break">
							<td colspan="2"></td>
						</tr>
						<tr class="break">
							<td colspan="2"></td>
						</tr>
					</c:forEach>
					
				</table>
	<div align="center">	
	<br/>		
<input type="submit" value="Submit"  value="Proceed" title="submits  the release details and resource vacation date to the database" class="btn btn-info dropdown-toggle">
</div>

</form:form>
<br/>
</div>
</body>
</html>