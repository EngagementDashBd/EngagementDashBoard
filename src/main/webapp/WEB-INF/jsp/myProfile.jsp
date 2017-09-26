 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--  <%@include file = "employeeTabs.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="css/allPage.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<!-- <script 
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
 
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 <script src="script/profile.js"></script> 
 
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
<style>
.myclass 
{
    text-transform:capitalize;
}

td{

width:200px;

}
#positions{
            width:70px;
            height:auto;
            text-align:center;
            top:20px;
            position:absolute;
            right:120px;
}

</style>

</head>
<body>
<c:if test="${ideaCode == 'success'}">
		<script>
			alert("Idea Added Successfully");
		</script>
	</c:if>
	<c:if test="${ideaCode == 'failure'}">
		<script>
			alert("Error..... Please Try again !!!");
		</script>
	</c:if>
 <c:if test="${code == 'success'}">
<script>
  alert("Updated Successfully.....");
</script>
</c:if>
<c:if test="${code == 'failure'}">
<script>
  alert("Update Failure.... Please Try again !!!");
</script>
</c:if> 
<c:if test="${code == 'invalidid'}">
<script>
  alert("invalid enterprise id !!!");
</script>
</c:if> 
<c:if test="${contactcode == 'success'}">
<script>
  alert("Updated Successfully.....");
</script>
</c:if>
<c:if test="${contactcode == 'failure'}">
<script>
  alert("Update Failure.... Please Try again !!!");
</script>
</c:if>  

<script>
		function populateSuperVisor() {

			$("#newSupervisor").autocomplete({
				// appendTo: "#newSupervisor", 	
				source : function(request, response) {
					$.ajax({
						type : "POST",
						minLength : 1,
						url : "SeachSupervisor.htm",
						data : {
							SUPID : $("#newSupervisor").val()
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
	
	$(function(){
	    $('#changeContact').on('click', function(){
	     
	      	document.getElementById('newContact').style.visibility = 'visible'; 
	      	document.getElementById('updateContact').style.visibility = 'visible'; 
	      	
	    	document.getElementById('changeContact').style.visibility = 'hidden'; 
	    	
	    });
	});
	</script>

	<div class="container" id="main">
	
	<div class="row">
			<div class="col-md-12">
				<br>
				<br>
				
				<div id="content" style="float:left; width:80%;" >
			

			<table cellspacing="50px" cellpadding="50">		
				
			<tr><!-- <td>Employee Name</td> --><td colspan="2" class="myclass"><h2><b><c:out value="${resource.employeeName}"></c:out></b></h2></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
		
			<tr><td><b>Enterprise Id</b></td><td><c:out value="${resource.enterpriseId}"></c:out></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
			<tr><td><b>Employee Id</b></td><td id="employeeId"><c:out value="${resource.employeeId}"></c:out></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
			
			
			
			<tr><td><b>Career Level</b></td><td><c:out value="${resource.level}"></c:out></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
			<tr><td><b>Project</b></td><td><c:out value="${projectName}"></c:out></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
			<tr><td><b>Designation</b></td><td><c:out value="${resource.careerLevel.designation}"></c:out></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
			<c:if test="${resource.passportNo != null}">
				<tr><td><b>Passport Number</b></td><td><c:out value="${resource.passportNo}"></c:out></td></tr>
			</c:if>
			<tr class="break"><td colspan="2"></td></tr>
			<c:if test="${resource.panNo != null}">
				<tr><td><b>PAN Number</b></td><td><c:out value="${resource.panNo}"></c:out></td></tr>
			</c:if>
			<tr class="break"><td colspan="2"></td></tr>
			<tr><td><b>Supervisor ID</b></td><td><c:out value = "${supervisorName}" ></c:out> </td><td><Button class="btn btn-info dropdown-toggle" id="changeSupervisor">Change supervisor</Button></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
			<c:if test="${resource.contactNo != null}">
				<tr><td><b>Mobile</b></td><td><c:out value = "${resource.contactNo}" ></c:out></td><td><Button class="btn btn-info dropdown-toggle" id="changeContact">Change Mobile Number</Button></td></tr>
			</c:if>
			<tr class="break"><td colspan="2"></td></tr>			
			</table> 
				<form name="supervisorchange" action="changeSupervisor.htm" method = "post">
				<input type="hidden" name="currentSupervisor" value="${supervisorName}"/>
			<input type="text" id="newSupervisor" name="newSupervisor" onkeyUp="populateSuperVisor(); "  style="visibility:hidden"/>
			<input class="btn btn-info dropdown-toggle" type="submit" id="updateSupervisor" value=" Update Supervisor" style="visibility:hidden"/>
				</form>
				<br>
				<form name="contactchange" action="changeContact.htm" method = "post">
			 <input type="text" id="newContact" name="newContact"   style="visibility:hidden"/>
			<input class="btn btn-info dropdown-toggle" type="submit" id="updateContact" value=" Update Contact" style="visibility:hidden"/>
	</form>
			
	</div>
	<br>
	<br>
	<div  id="positions"  >
	<c:if test="${resource.base64Jmage != null}">
		<img alt="img" src="data:image/jpeg;base64,${resource.base64Jmage}" height="200" width="160"/>
	</c:if>
	</div>
	</div>
	</div>
	</div>
	
</body>
</html>