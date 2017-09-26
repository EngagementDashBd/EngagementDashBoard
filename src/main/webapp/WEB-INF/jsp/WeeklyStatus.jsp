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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript"
	src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script>
<link rel="stylesheet" type="text/css" href="css/weekly.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<style>
.tableheader {
	position: static;
	text-align: center;
	border-radius: 5px;
	border: 0px solid #73AD21;
	padding: 7px;
	background-color: Gainsboro;
	width: 33%;
}
</style>


<script>

	 $(function() {

		
		 
		var dateFormat = "dd-mm-yy",
		from = $("#from")

		.datepicker({
			changeMonth : true,
			numberOfMonths : 1, 
			dateFormat: 'dd-mm-yy',
			maxDate: datepick,
		})
		.on("change", function() {
			to.datepicker("option", "minDate", getDate(this));
		}),

		to = $("#to").datepicker({
			changeMonth : true,
			numberOfMonths : 1, 
			dateFormat: 'dd-mm-yy',
		})
		.on("change", function() {
			from.datepicker("option", "maxDate", getDate(this));
		}),
		
		datepick = $("#datepicker").datepicker({
			changeMonth : true,
			numberOfMonths : 1, 
			dateFormat: 'dd-mm-yy'
		})
		.on("change", function() {
			to.datepicker("option", "maxDate", getDate(this));
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


	function getResoursebyProjectID() {
		//alert("this is n=inside function1");
		$('#content').load('addResourceTaskandVacation.htm', {
			PROID : $("#programId").val()
		});

	}

	function wsrFormIntoSession() {

		$.ajax({
			type : "post",
			url : "saveWeeklyStatus.htm",
			data : $('#wsrDetails').serialize(),
	

		});
	}

var mdate;

$( function() {
   $( "#datepicker1" ).datepicker({   
 	 showOn: "both",
 
 	beforeShowDay: $.datepicker.noWeekends,
 	beforeShow: function(){},
 	autoSize: true,
 	changeMonth: true,
 	changeYear:true,
 	gotoCurrent: true,
 	onSelect: function(){

 $( "#datepicker2" ).datepicker( "option", "minDate", new Date($(this).val()) );
  $( "#weekStart").datepicker( "option", "maxDate", new Date($(this).val()) );
 
  }

});

} );
 
$( function() {
   $( "#datepicker2" ).datepicker({  
   showOn: "both",

  beforeShow: function(){},
  autoSize: true,
  onSelect: function(){$( "#weekStart" ).datepicker( "option", "maxDate", new Date($(this).val()) );
  $( "#datepicker1").datepicker( "option", "maxDate", new Date($(this).val()) )
  }
   });

} );
$( function() {
	   $( "#weekStart" ).datepicker({   
	   showOn: "both",
	  
	  beforeShowDay: $.datepicker.noWeekends,
	  beforeShow: function(){},
	  autoSize: true,
	  changeMonth: true,
	  changeYear:true,
	 gotoCurrent: true,
	 onSelect: function(){

		 $( "#datepicker1").datepicker( "option", "minDate", new Date($(this).val()) );
		  $( "#datepicker2").datepicker( "option", "minDate", new Date($(this).val()) );
		  $( "#datepicker2").datepicker( "option", "minDate", new Date($("#datepicker1").val()) )},

	});

	} );

	function getWSRbyProjectName() {

		$('#wsrcontent').load('getWsrRecords.htm', {
			programNames : $("#projectName").val()
		});
	}
</script>
<style>
.textbox {
	box-shadow: 1px 1px 45px #333;
	transition: 200ms all ease;
	border: none;
	padding: 0px 0px;
}

.textbox {
	height: 29px;
	width: 250px;
}

.textbox {
	box-shadow: 1px 1px 45px #333;
	transition: 200ms all ease;
	border: none;
	padding: 5px 5px;
}
</style>

<title>Weekly Status Report</title>
</head>
<body>

	<c:if test="${code == 'success'}">
		<c:set var="code" value="emPty">
		</c:set>
		<script>
			alert("Record Added  Successfully.");
		</script>

	</c:if>
	<c:if test="${code == 'failure'}">


		<script>
			alert("Record Added Unsuccessfully.... Please Try again !!!");
		</script>
	</c:if>
	<c:if test="${setTab == 'resourceTab'}">
		<script>
			openTab(event, 'resourceTab');
		</script>
	</c:if>
<h4><strong>Release Details</strong></h4>
	<form:form method="POST" action="addResourceTaskandVacation.htm"
		id="wsrDetails" modelAttribute="wsrDetails">

		<table id="table">
			<tr class="break">
				<td colspan="2"></td>
			</tr>
			<tr>
				<td><b> Project Name</b></td>
				<td><select id="programId" name="applicationName" class="tex"
					onchange="getResoursebyProjectID()" required>
						<!-- <option>Select Project Name</option> -->
						<option value="">Select Project Name</option>
						<c:forEach items="${ProjectNames}" var="ProjectNames">
							
							<option value="${ProjectNames.id}">${ProjectNames.projectName}</option>
						</c:forEach>
				</select></td>

			</tr>
			<tr class="break">
				<td colspan="2"></td>
			</tr>
			<tr>
				<td><label>Release Name:</label></td>
				<td><input type="text" class="textbox" id="releaseName"
					name="releaseName" required></td>
			</tr>
			<tr class="break">
				<td colspan="2"></td>
			</tr>
			<tr>
				<td><label>Release Week Start:</label></td>
				<td><input type="text" name="weekStart" id="weekStart" required></td>
			</tr>
			<tr class="break">
				<td colspan="2"></td>
			</tr>
			<tr>
				<td><label>Release Week End:</label></td>
				<td><input type="text" name="weekend" id="datepicker1" required></td>
			</tr>
			<tr class="break">
				<td colspan="2"></td>
			</tr>
			<tr>
				<td><label>Release go Live:</label></td>
				<td><input type="text" name="releaseDate" id="datepicker2"
					required></td>
			</tr>
			<tr class="break">
				<td colspan="2"></td>
			</tr>

			<tr>
				<td><label>Project Accomplishments:</label></td>
				<td><textarea rows="3" cols="40" name="projectAccomplishment"
						id="projectDescription" class="tb" required></textarea></td>
			</tr>
			<tr class="break">
				<td colspan="2"></td>
			</tr>
			<tr>
				<td><label>Next week Plan:</label></td>
				<td><textarea rows="3" cols="80" name="nextWeekPlan"
						id="projectDescription" class="tb" required></textarea></td>
			</tr>
			<tr class="break">
				<td colspan="2"></td>
			</tr>
			<tr>
				<td><label>Risk/Impediments:</label></td>
				<td><textarea rows="3" cols="80" name="risk" id="impediments"
						class="tb" required></textarea></td>
			</tr>

			<tr class="break">
				<td colspan="2"></td>
			</tr>


			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Proceed"
					title="Click to save data and proceed to resource task form."
					class="btn btn-info dropdown-toggle"
					onclick=" return wsrFormIntoSession()"></td>
			</tr>
			<tr class="break">
				<td colspan="2"></td>
		</table>
	</form:form>



</body>
</html>