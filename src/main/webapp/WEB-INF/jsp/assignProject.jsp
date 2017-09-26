<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="css/validationmsg.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" href="css/jquery.filer.css" >

<link href="css/jquery.filer-dragdropbox-theme.css" rel="stylesheet">

<link rel="stylesheet" href="css/allPage.css">
<link rel="stylesheet" href="css/error.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<title>New Employee</title>




<!-- New Import -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="script/custom.js" type="text/javascript"></script>
<script src="script/jquery.filer.min.js" type="text/javascript"></script>
 <link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/jquery.validate.min.js"/>"></script>

<style>

td {
	padding: 5px 0;
} 

#content {
	border: 1px solid #000;
	padding: 5px;
}
</style>

<script type="text/javascript">
	function goBack() {
		window.history.back();
	}
</script>
<script>
	$(document).ready(function() {

		$(function() {
			$('.datepik').datepicker({
				viewMode : 'days',
				format : 'dd/mm/yy',
				showTodayButton : true,
				showOn : "button"

			});
		});
	});
</script>
<script>
	$(document)
			.ready(
					function() {
						$.validator.addMethod("pattern", function(value,
								element, regexpr) {

							return this.optional(element)
									|| regexpr.test(value);
						}, "Please enter a valid value.");

						$("#addEmployee")
								.validate(
										{

											rules : {
												
												
												"enterpriseId" : {
													required : true,
													pattern : /^[a-zA-Z](?:.?[a-zA-Z]+)*$/,
													minlength : 3,
													maxlength : 30

												},
												
												"roleName" : {
													required : true
													},

												"lanId" : {
													required : true
												},

												"lcr" : {
													required : true,
													pattern : /^[0-9]\d{0,9}(\.\d{1,2})?%?$/,
													maxlength : 10
												},
												
												
												"projectId" : {
													required : true
												},
												"locationId":{
													required:true
												},
												"roleStartDate":{
													required:true
												},
												"roleEndDate":{
													required:true
												},

											},
											messages : {
											

												"enterpriseId" : {
													required : 'Enterprise Id is required',

													maxlength : 'Enterprise Id should be maximum 30 characters.',
													pattern : 'Enterprise Id should be in alphabets'
												},

												"level" : {
													required : 'Career level should be provided',
													range : 'Your career level must be between 5 and 12.',

												},
												"roleName" : {
													required : 'Project Role Name is required ',
													pattern : 'Project Role should be in alphabets'
												},

												"lanId" : {
													required : 'LAN ID is required'
												},
												
												"lcr" : {
													required : 'LCR is required',
													pattern : 'LCR should be in decimal',
													maxlength : 'LCR should be maximum 10 digits'
												},
												
												"projectId" : {
													required : 'Please choose a project.'
												},
												"locationId":{
													required:'Please choose a location.'
												},
												"roleStartDate":{
													required:'Please fill the Start date.'
												},
												"roleEndDate":{
													required:'Please fill the end date.'
												}
											},
											errorContainer : $('#errorContainer'),
											errorLabelContainer : $('#errorContainer ul'),
											wrapper : 'li'

										});
					});
</script>


<script>
	function populateSuperVisor() {

		$("#supervisorId").autocomplete({
			// appendTo: "#newSupervisor", 	
			source : function(request, response) {
				$.ajax({
					type : "POST",
					minLength : 1,
					url : "SeachSupervisor.htm",
					data : {
						SUPID : $("#supervisorId").val()
					},
					success : function(data) {
						response(data);

					}

				});
			}
		});

	}
</script>
<script>
	function viewHistory(value) {
		$('#history').load('getProjectHistory.htm', {
			employeeId : value
		}, function() {

			$("#history").dialog({
				width : 850,
				height : 550,				
				title : 'Project Details'
			});
			}
		);

	}

</script>

</head>
<body>


	<div class="container" id="main">
		<center>
			<h2>
				<b> New Employee Register </b>
			</h2>
		</center>
		<div class="row">
			<div class="col-md-12">
				<br> <br>

				<div id="errorContainer">
					<p>Please correct the following errors and try again:</p>
					<ul />
				</div>
				<div id="content">
					<br>
					<a id="${resourceMaster.employeeId}"  onclick="return  viewHistory(this.id);">View History</a>
					<form name="empform" action="assignProject.htm" method="post"
						id="addEmployee">
						<input type="hidden" name="employeeId"
							value="${resourceMaster.employeeId}">
						<table>
							<tr>
								<td><label for="enterprise_id">Enterprise Id</label></td>
								<td><input class="textBox" type="text" name="enterpriseId"
									value="${resourceMaster.enterpriseId }" disabled></td>
							</tr>
							<tr>

								<td><label for="Role Start_date">Role Start date</td>
								<td>
									<div class="form-group">
										<div class='input-group date '>
										<fmt:formatDate value="${employeeProject.roleStartDate}" pattern="MM/dd/yyyy"  var="startDate"/>
										<input type='text' id="roleStartDate" class="form-control datepik" value="${startDate}" name="roleStartDate"/>
											
										</div>
									</div>
								</td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr>



								<td><label for="Role End date">Role End date</td>
								<td>
									<div class="form-group">
										<div class='input-group date '>
										<fmt:formatDate value="${employeeProject.roleEndDate}" pattern="MM/dd/yyyy"  var="endDate"/>
											<input type='text' id="roleEndDate" class="form-control datepik" value="${endDate}" name="roleEndDate"/> 
											
											<!-- <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span> -->
										</div>
									</div>
								</td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>

							<tr>
								<td><label for="Project Role">Access Now Role</label></td>
								<td>
								<select class="textBox" name="roleName" required>
									<c:if test="${empty employeeProject.roleName}">
										<option value="">--Select a Role--</option>
									</c:if>
									<c:if test="${not empty employeeProject.roleName}">
										<option value="${employeeProject.roleName}">${employeeProject.roleName}</option>
									</c:if>
										<option value="V0000005">V0000005</option>
										<option value="V0000007">V0000007</option>

								</select>
								</td>
							</tr>

							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr>

								<td><label for="Client id">LAN ID</label></td>
								<td><input class="textBox" type="text" name="lanId" value="${employeeProject.lanId }"
									required></td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>

							<tr>
								<td><label for="Lcr">LCR</label></td>
								<td><input type="text" name="lcr" class="textBox" id="lcr"  value="${employeeProject.lcr }"
									required></td>

							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr>
								<td><label for="Project">Project</td>
								<td><select class="textBox" name="projectId" id="projectId">
										<option value="">--select a project---</option>										
										<c:forEach items="${projects}" var="projects">
											<c:if test="${projects.id == employeeProject.projectId}">
												<option value="${projects.id}" selected>${projects.projectName}</option>
											</c:if>
											<c:if test="${projects.id != employeeProject.projectId}">
												<option value="${projects.id}">${projects.projectName}</option>
											</c:if>
										</c:forEach>
								</select></td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr>
								<td><label for="Location">Location</td>
								<td><select class="textBox" name="locationId" required>
								<option value="">--select a location---</option>										
										<c:forEach items="${locations}" var="locations">
											<c:if test="${locations.locationId == resourceMaster.locationId}">
												<option value="${locations.locationId}" selected>${locations.locationName}</option>
											</c:if>
											<c:if test="${locations.locationId != resourceMaster.locationId}">
												<option value="${locations.locationId}">${locations.locationName}</option>
											</c:if>
										</c:forEach>

								</select></td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr align="center">
								<td colspan="2"><input class="btn btn-info dropdown-toggle"
									type="submit" value="Save">&nbsp;&nbsp; <a
									href="javascript:goBack(); "
									class="btn btn-info dropdown-toggle">Cancel</a></td>
							</tr>

						</table>

					</form>


				</div>
				<br> <br>
			</div>
		</div>
		<div id="history"></div>
	</div>


</body>
</html>