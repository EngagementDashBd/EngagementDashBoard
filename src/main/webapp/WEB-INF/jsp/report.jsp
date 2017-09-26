<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import = "com.acc.entity.ResourceMaster"%>
<sec:authorize access="hasRole('ADMIN')">

</sec:authorize>
<html>
<head>
<title>Welcome to Attendance Tracker</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="css/report.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
<!-- <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>

 
 
 
</head>
<body>
<script>
$(document).ready(function() {
	
	$("#midRangeReport").submit(function(){
		var startMonth = document.getElementById("startMonthName").value;
		var endMonth = document.getElementById("endMonthName").value;
		var monthName = [ "January", "February", "March", "April", "May", "June", "July",
		        "August", "September", "October", "November", "December" ];
		var startMonthYearSplit = startMonth.split(",");
		var endMonthYearSplit = endMonth.split(",");
		var startMonthIndex = monthName.indexOf(startMonthYearSplit[0])
		var endMonthIndex = monthName.indexOf(endMonthYearSplit[0])
		if(((endMonthIndex - startMonthIndex) % 11 == 1) && (endMonthYearSplit[1] - startMonthYearSplit[1] == 0))
			return true;
		else if(((endMonthIndex - startMonthIndex) % 11 == 0) && (endMonthYearSplit[1] - startMonthYearSplit[1] == 1))
			return true;
		else
		{
			alert("Please Select Consecutive Months !!")
			return false;
		}
		
	});
});
</script>

	<div class="container">
		<h2 align="center">Attendance-Tracker Reports</h2>
	<!-- <form id = "reportForm" action = "generateReport.htm" method = "post"> -->
	<form id = "reportForm" action = "generateReport.xls" method = "post">
		<div class="panel panel-info">
			<div class="panel-heading" align="center">Monthly Reports</div>
			<div class="panel-body">
				<label for="month">Select Particular Month:</label>
				<div class="form-group">
					<div class='input-group date monthPicker'>
						<input type='text' class="form-control" name = "monthName" id = "monthName" required/> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>

			</div>
			<div class="panel-footer">
				<div class="btn-group btn-group-info">
	  <button class="btn btn-info dropdown-toggle" data-toggle="dropdown" type="button">Generate Reports</button>
		  <ul class="dropdown-menu">
		  <li><input class="btn btn-info dropdown-toggle" type="submit" value="Download as PDF" name = "submit"></li>
		  <li class="divider"></li>		  
	  <li><input class="btn btn-info dropdown-toggle" type="submit" value="Download as Excel" name = "submit"></li>
	  </ul>
	
  </div>
			</div>
		</div>
</form>

<form id = "midRangeReport" action = "generateMidRangeReport.xls" method = "post">
		<div class="panel panel-info">
			<div class="panel-heading" align="center">MidRange Reports</div>
			<div class="panel-body">
				<label for="month">From 16th of :</label>
				<div class="form-group">
					<div class='input-group date monthPicker'>
						<input type='text' class="form-control" name = "startMonthName" id = "startMonthName" required/> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
				<label for="month">To 15th of :</label>
				<div class="form-group">
					<div class='input-group date monthPicker'>
						<input type='text' class="form-control" name = "endMonthName" id = "endMonthName" required/> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>

			</div>

			<div class="panel-footer">
			<div class="btn-group btn-group-info">
	  <button class="btn btn-info dropdown-toggle" data-toggle="dropdown" type="button">Generate Reports</button>
		  <ul class="dropdown-menu">
		  <li><input class="btn btn-info dropdown-toggle" type="submit" value="Download as PDF" name = "submit"></li>
		  <li class="divider"></li>		  
	  <li><input class="btn btn-info dropdown-toggle" type="submit" value="Download as Excel" name = "submit"></li>
	  </ul>
	  </div>
			</div>
		</div>
	</form>
		
	<!-- 	</form> -->
	</div>

	<script type="text/javascript">
			$(document).ready(function() {
				$(function() {
					$('#datetimepicker1').datetimepicker({
						format : 'DD/MM/YYYY'
					});
				});
				$(function() {
					$('.monthPicker').datetimepicker({
						viewMode: 'months',
						format : 'MMMM,YYYY',
						showTodayButton : true,
					});
				});
				$(function() {
					$('#fortNightPickerStart').datetimepicker({
						viewMode: 'days',
						format : 'DD/MM/YYYY',
						showTodayButton : true,
					});
				});
				$(function() {
					$('#fortNightPickerEnd').datetimepicker({
						viewMode: 'days',
						format : 'DD/MM/YYYY',
						showTodayButton : true,
					});
				});
			});
		</script>

</body>
</html>