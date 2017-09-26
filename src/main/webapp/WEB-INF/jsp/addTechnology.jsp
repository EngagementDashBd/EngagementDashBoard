<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
  
<link href="css/jquery.filer.css" rel="stylesheet">
<link href="css/jquery.filer-dragdropbox-theme.css" rel="stylesheet">
<link rel="stylesheet" href="css/allPage.css">
<link href="css/bootstrap-min-3.3.7.css" rel="stylesheet">
<link href="css/font-awesome-min4.7.0.css" rel="stylesheet">

<script src="script/custom.js" type="text/javascript"></script>
<script src="script/jquery.filer.min.js" type="text/javascript"></script>
<script src="javascript/bootstrap.min-3.3.7.js" type="text/javascript"></script>
<script src="javascript/jquery-1.12.4.js" type="text/javascript"></script>
<script src="javascript/jquery-ui-1.12.1.js" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/script/jquery.validate.min.js"/>"></script>
<link rel="stylesheet" href="css/validationmsg.css">
<script type="text/javascript">
function goBack() {
    window.history.back();
}
</script>
<script type="text/javascript">

$(document).ready(function() {
	$.validator.addMethod("pattern", function(value, element, regexpr) {  
		
	     return this.optional( element ) || regexpr.test(value);
	   }, "Please enter a valid value."); 

	
	$("#addtechnology").validate({
	
		 rules: {
			 
			 "technologyName": {
					 required: true,
					 pattern: /^[a-z A-Z]+$/,
					 minlength:3,
					 maxlength:30
				 },
				 
				
				 "technologyDescription": {
		            	required:true,
		            	pattern: /^[a-z A-Z]+$/,
		            	maxlength:50
		            }
		           
		    	},
		    	
			messages:{
				
				"technologyName" : {
					required: 'Technology Name is required.',
					minlength: 'Technology Name should be minimum 3 characters.',
					maxlength:' Technology Name should be maximum 30 characters.',
					pattern: 'Technology Name must be in alphabets.'
				},
				
								 
				 "technologyDescription": {
						required: 'Technology Description is required.',
						maxlength: 'Technology Description should be maximum  50 characters.'
				}	
							
				
			},
			errorContainer: $('#errorContainer'),
		    errorLabelContainer: $('#errorContainer ul'),
		    wrapper: 'li'
			
});
});
</script>

</head>
<body>
	<c:if test="${code == 'success'}">
		<script>
			alert("added successfully");
		</script>
	</c:if>
	<c:if test="${Code == 'failure'}">
		<script>
			alert("Error..... Please Try again !!!");
		</script>
	</c:if>
	
	<div class="container" id="main">
	<br>
	<br>
	<center><h2><b>Add New Technology</b></h2></center>
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
					<form action="addTechnology.htm"  id="addtechnology" method="post">
						<table>
							<tr>
								<td>Technology Name <span style="color: red;">*</span></td>
								<td><input  name="technologyName" class = "textBox" type="text" 
									required></td>
							</tr>
							
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr>
								<td>Description <span style="color: red;">*</span></td>
								<td><textarea rows="4" cols="50"  name="technologyDescription" class = "textBox" ></textarea></td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr align="center">							
								<td colspan="2"><input class="btn btn-info dropdown-toggle"
									type="submit" value="Save">&nbsp;&nbsp;<a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></td>
							</tr>							


						</table>
					</form>


				</div>
				<br> <br>
			</div>
		</div>
	</div>


</body>
</html>