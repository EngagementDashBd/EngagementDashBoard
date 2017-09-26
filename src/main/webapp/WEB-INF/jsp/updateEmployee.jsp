<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<link rel="stylesheet"	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
	<!--  <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
 <!--  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
 
 <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	
<!--  <script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script> 

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	<script src="javascript/jquery-1.12.4.js" type="text/javascript" ></script>
<script type="text/javascript" src="javascript/jquery-ui-1.12.1.js"></script>
<script type="text/javascript" src="javascript/moment.min-2.10.6.js"></script>
<script type="text/javascript" src="javascript/bootstrap.min-3.3.7.js"></script>

<link href="css/jquery.filer.css" rel="stylesheet">
<link href="css/jquery.filer-dragdropbox-theme.css"
	rel="stylesheet">
<title>Update Employee</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/allPage.css">
<script src="script/custom.js" type="text/javascript"></script>
<script src="script/jquery.filer.min.js" type="text/javascript"></script>


<script type="text/javascript" src="<c:url value="/script/jquery.validate.min.js"/>"></script>
<link rel="stylesheet" href="css/error.css">
<link rel="stylesheet" href="css/validationmsg.css">
<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" /> 
 <script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>  -->

<style>
/* table {
  border-collapse: separate;
 
  border-spacing: 30px 0;
} */
td{
   padding: 5px 0;
}
form {
  border : 1px solid #000;
  padding : 5px;
}

</style>
<script type="text/javascript">
function goBack() {
    window.history.back();
}
</script>
<script>
	$(document).ready(function() {
		$("#updateEmployee").submit(function() {
			if (document.getElementById("projectId").value == '')
				document.getElementById("projectId").value = 0;
			if (document.getElementById("roleStartDate").value == '')
				document.getElementById("roleStartDate").value = '01/01/1970';
			if (document.getElementById("roleEndDate").value == '')
				document.getElementById("roleEndDate").value = '01/01/1970';
		});

	});
</script>
<script>

	
	$(document).ready(function() {
		$.validator.addMethod("pattern", function(value, element, regexpr) {  
			
		     return this.optional( element ) || regexpr.test(value);
		   }, "Please enter a valid value."); 

	
	$("#updateEmployee").validate({
	
		rules: {
			 
	           "level":{
	        	   required:false,
	        	   range: [5,12]
	        	   
	           },
	           "supervisorEnterpriseId":{
	        	   required:false,
	        	   pattern:/^[a-zA-Z](?:.?[a-zA-Z]+)*$/
	           },
	           
	           "technologyId":{
	        	   required:false
	           },
	               
	               
			
	   		},
		
	   		messages:{
					
	   		
	   		"level": {
				required: 'Career Level should be required',
				range: "Your career level must be between 5 and 12."
				
		},

		"supervisorEnterpriseId": {
				required: 'Please provide Supervisor name.'
	},
	
	"technologyId":{
		required:'Please choose a technology.'
	},
	            
		},
		errorContainer: $('#errorContainer'),
	     errorLabelContainer: $('#errorContainer ul'),
	     wrapper: 'li'
	   	
});
});	
	
	</script>
	<!-- <script>
			$(document).ready(function() {
				
				$(function(){
					$('.datepik').datepicker({
						viewMode: 'days',
						format : 'dd/mm/yy',
						showTodayButton : true,
						showOn: "button"

					});
				});
			});
</script> -->
<script>
		
			function populateSuperVisor() {

			$("#supervisorName").autocomplete({
				// appendTo: "#newSupervisor", 	
		
				source : function(request, response) {
					//alert($("#supervisorName").val())
					$.ajax({
						type : "POST",
						minLength : 1,
						url : "SeachSupervisor.htm",
						data : {
							SUPID : $("#supervisorName").val(),
							level : $("#level").val()
						},
						success : function(data) {
							//alert(data);
							response(data);

						}

					});
				}
			});

		}
	
	</script>
	
</head>
<body>
	<div class="container" id="main">
	<center><h1><b>UPDATE EMPLOYEE</b></h1></center>
		<div class="row">
			<div class="col-md-12">
			<br>
		    <br>
				
				<div id="errorContainer">
    <p>Please correct the following errors and try again:</p>
    <ul/>
	</div>		
				<div id="content">
				
	             <br>		
					<form action="updateEmployee.htm" method="post" id="updateEmployee">
						<table>
							<tr><td><b>Employee Name</b></td><td><input id = "employeeName" type="text" name="name" class="textBox" value="${resourceToBeEdited.employeeName}" disabled></td>
							<td>&nbsp;</td>
                        <!--  <td><label for="Role Start_date">Role Start_date</td> -->
				<%-- <td>
				<div class="form-group">
					<div class='input-group date ' >
					<fmt:formatDate value="${employeeProject.roleStartDate}" pattern="yyyy-MM-dd"  var="startDate"/>
						<input type='text' id="roleStartDate" class="form-control datepik" value="${startDate}" name="roleStartDate"/>
					</div>
				</div>
				</td> --%></tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr><td><b>Employee ID</b></td><td><input id = "employeeId" type="text" name="empid" class="textBox" value="${resourceToBeEdited.employeeId}" disabled></td>
							<td>&nbsp;</td>
                        <%--  <td><label for="Employee Name">Role End_date</td>
				<td>
				<div class="form-group">
					<div class='input-group date '>
						<fmt:formatDate value="${employeeProject.roleEndDate}" pattern="yyyy-MM-dd"  var="endDate"/>
						<input type='text' id="roleEndDate" class="form-control datepik" value="${endDate}" name="roleEndDate"/> 
					</div>
				</div>
				</td> --%></tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr><td><input type = "hidden" name= "employeeName" class="textBox" value="${resourceToBeEdited.employeeName}"/></td></tr>
							<tr><td><input type = "hidden" name= "employeeId" class="textBox" value="${resourceToBeEdited.employeeId}"/></td></tr>
							<tr><td><input type = "hidden" name= "enterpriseId" class="textBox" value="${resourceToBeEdited.enterpriseId}"/></td></tr>
							<tr><td><b>Enterprise Id</b></td><td><input class="textBox" id = "enterpriseId" type="text" name="enterid" value="${resourceToBeEdited.enterpriseId}" disabled></td></tr>
							
			<!-- 	<td><label for="Project Role">Access Now Role</label></td> -->
				<%-- <td>
				<select class="textBox" name="roleName" value="${employeeProject.roleName}" required>
										<option value="">--Select a Role--</option>
										<option value="V0000005">V0000005</option>
										<option value="V0000007">V0000007</option>

								</select> --%>
				
										<tr class="break"><td colspan="2"></td></tr>
							<tr><td><b>Career Level</b></td><td>
								<select class = "textBox" name="level" id="level"  required>
										<option value="${resourceToBeEdited.level}">${resourceToBeEdited.level}</option>
  										<option value="12">12</option>
  										<option value="11">11</option>
  										<option value="10">10</option>
  										<option value="9">9</option>
  						  				<option value="8">8</option>
 										<option value="7">7</option>
 										<option value="6">6</option>
 										<option value="5">5</option>
									</select>
							
							<td>&nbsp;</td>
							<%-- <td><label for="Client id">LAN ID</label></td><td><input class = "textBox" type="text" name="lanId" value="${employeeProject.lanId}"></td> --%>
						</tr>
							<tr class="break"><td colspan="2"></td></tr>							
							<tr><td><b>Supervisor Name</b></td><td><input class="textBox" id = "supervisorName" type="text" name="supervisorEnterpriseId" value="${supervisorName}" onkeyUp="populateSuperVisor();"></td>
							<%-- <td>&nbsp;</td>
							<td><b>Project</b></td>
								<td>
									<select  id = "projectId" name="projectId" class="textBox" required>
									<option value="${resource.projectId}">${ProjectName}</option>
 										<c:forEach items="${projects}" var="projects">
 											<c:if test = "${projects.projectName != ProjectName}">
        										<option value="${projects.id}">${projects.projectName}</option>
        									 </c:if>
        									 <c:if test = "${projects.projectName == ProjectName}">
        										<option value="${projects.id}" selected>${projects.projectName}</option>
        									 </c:if>
										</c:forEach>
									</select>			  

								</td> --%>
								
							</tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr><td><label for="Technology">Technology</td>
							<td>						
									<select  class = "textBox" name="technologyId" required>
									<option value="">--select an option---</option>
 										<c:forEach items="${technologies}" var="technology">
 										<c:if test = "${technology.technologyId != resourceToBeEdited.technologyId}">
        										<option value="${technology.technologyId}">${technology.technologyName}</option>
        									 </c:if>
        									 <c:if test = "${technology.technologyId == resourceToBeEdited.technologyId}">
        										<option value="${technology.technologyId}" selected>${technology.technologyName}</option>
        									 </c:if>
 										
											
										</c:forEach>
									</select>	
							</td>
							<td>&nbsp;</td>
						<%-- <td><label for="Location">Location</td><td>						
									<select  class = "textBox" name="locationId" value="${resourceToBeEdited.locationId}">
									<option value="">--select a loction--</option>
 									<option value="1">Chennai</option>
 									<option value="2">Hyderabad</option>
									</select>	</td>  --%>
							<%-- <c:if test = "${resource.employeeName != 'Administrator'}">
							<tr><td><b>LCR:</b></td><td><input class="textBox" id = "lcr" type="text" name="lcr" value="${employeeProject.lcr}"><td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							 </c:if> --%>
							</tr>
								<tr class="break"><td colspan="2"></td></tr>
						
								<tr class="break"><td colspan="4"></td></tr>
						
						<tr align="center"><td colspan="4"><input class="btn btn-info dropdown-toggle" type="submit" value="Update">&nbsp;&nbsp;<a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></td></tr>			
							<tr class="break"><td colspan="2"></td></tr>
						</table>					
					</form>
					

				</div>
				<br>
				<br> 
			</div>
		</div>
	</div>
</body>
</html>