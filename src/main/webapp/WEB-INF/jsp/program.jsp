<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="css/jquery.filer.css" rel="stylesheet">
<link href="css/jquery.filer-dragdropbox-theme.css" rel="stylesheet">
<link rel="stylesheet" href="css/allPage.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="script/custom.js" type="text/javascript"></script>
<script src="script/jquery.filer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/jquery.validate.min.js"/>"></script>
<link rel="stylesheet" href="css/error.css">
<link rel="stylesheet" href="css/validationmsg.css">
<script type="text/javascript">
function goBack() {
    window.history.back();
}
</script>
<script>
$(document).ready(function() {
	$.validator.addMethod("pattern", function(value, element, regexpr) {  
		
	     return this.optional( element ) || regexpr.test(value);
	   }, "Please enter a valid value."); 

	
	$("#program").validate({
	
		 rules: {
			 
			 "programName": {
					 required: true,
					 pattern: /^[a-z A-Z]+$/,
					 minlength:3,
					 maxlength:30
				 },
				 
				 "portfolioId" : {
				      required:true
				 },
				"programDescription":{
		            	required:true,
		            	pattern: /^[a-z A-Z]+$/,
		            	maxlength:50
		            }
		           
		    	},
			messages:{
				
				"programName" : {
					required: 'Program Name is required.',
					minlength: 'Program Name should be minimum 3 characters.',
					maxlength:' Program Name should be maximum 30 characters.'
				},
				
				
				 "portfolioId" : {
				      required:'Please choose Portfolio Name',
				 },
				 
				"programDescription": {
						required: 'Program Description is required.',
						maxlength: 'Program Description should be maximum  50 characters.'
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
	<center><h2><b>Add New Program</b></h2></center>
		<div class="row">
			
			<div class="col-md-12">
				<br> <br> 
				<div id="errorContainer">
    <p>Please correct the following errors and try again:</p>
    <ul/>
	</div>	
				<div id="content">
					<form action="addProgram.htm" id="program">
						<table style="">
							<tr>
								<td>Program Name <span style="color: red;">*</span></td>
								<td><input id="programName" name="programName" class = "textBox" type="text" 
									required></td>
							</tr>
							
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr>
								<td>Portfolio Name <span style="color: red;">*</span></td>
								<td><select id="portfolioId" name="portfolioId" class = "textBox" required>
										<option value="">---select a portfolio---</option>
										<c:forEach items="${portfolios}" var="portfolio">
											<option value="${portfolio.portfolioId}">${portfolio.portfolioName}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr>
								<td>Description:</td>
								<td><textarea rows="4" cols="50" class = "textBox" name="description"></textarea></td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr align="center">
							
								<td colspan="2"><input class="btn btn-info dropdown-toggle"
									type="submit" value="Save">&nbsp;&nbsp;
									<a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></td>
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