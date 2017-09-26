<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@include file = "adminTabs.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projectwise Statistics</title>
<!-- <link href="css/bootstrap-min-3.3.7.css" rel="stylesheet">
<link rel="stylesheet" href="css/statistics.css">
<link href="css/font-awesome-min4.7.0.css" rel="stylesheet">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<script
		src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>

<script src="javascript/jquery-min-1.11.2.js" type="text/javascript"></script>
<script src="javascript/bootstrap-min-3.3.2.js" type="text/javascript"></script>
<script src="javascript/charts-loader.js" type="text/javascript"></script> -->
 <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/statistics.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
	 var myFunction = function drawChart() {
	
    	  var jsonData = $.ajax({
              url: "shiftsdata.htm",
              dataType: "json",
              async: false
              }).responseText;
          var data = new google.visualization.DataTable(jsonData);
          var options = {
          title: 'HCSC Shift Details for the Current Month'
         };
		var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
		chart.draw(data, options);
      }
</script>
<script>
$(document).ready(function() {
$("#portfolio").change(function () {
	  var jsonData = $.ajax({
         url: "programWiseShiftsData.htm",
         dataType: "json",
         data:{portfolioId : $("#portfolio").find("option:selected").val()},
         async: false
         }).responseText;
     var data = new google.visualization.DataTable(jsonData);
     var options = {
     title: 'HCSC Program Shift Allowance Details for the Current Month'
    };
    // alert(data);
	var chart = new google.visualization.PieChart(document.getElementById('portfolioChart'));
	chart.draw(data, options);
 });
});
</script>
<script>
$(document).ready(function() {
$("#program").change(function () {
	  var jsonData = $.ajax({
         url: "projectWiseShiftsData.htm",
         dataType: "json",
         data:{programId : $("#program").find("option:selected").val()},
         async: false
         }).responseText;
     var data = new google.visualization.DataTable(jsonData);
     var options = {
     title: 'HCSC Project Shift Allowance Details for the Current Month'
    };
   //  alert(data);
	var chart = new google.visualization.PieChart(document.getElementById('portfolioChart'));
	chart.draw(data, options);
 });
});
</script>
        
  </head>
  <body>
  	<div class="container" id="main">
		<div class="row">
			<div class="col-md-12">
  	<center><h2><b>HCSC Shift Allowance Details</b></h2></center>
    <br>
    <br>
   
   <label for="Portfolios">Portfolios</label>
   <select  class = "textBox" name="portfolio" id="portfolio">
	<option value="">--select an option---</option>
	<c:forEach items="${allPortfolios}" var="portfolio">
		<option value="${portfolio.portfolioId}" >${portfolio.portfolioName}</option>
	</c:forEach>
	</select>
	
   <label for="Programs">Programs</label>
   <select  class = "textBox" name="program" id="program" >
	<option value="">--select an option---</option>
	<c:forEach items="${allPrograms}" var="program">
	<option value="${program.programId}">${program.programName}</option>
	</c:forEach>
	</select>
	<div id="portfolioChart" style="width: 900px; height: 500px;"></div>
	<br>
	<!-- <br>
	<div id="programChart" style="width: 900px; height: 500px;"></div> -->
	
	
      
	</div></div></div>	
  </body>
</html>