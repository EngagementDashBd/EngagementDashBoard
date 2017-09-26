<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

<link rel="stylesheet"	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<!-- <link rel="stylesheet" href="css/style.css"> -->
<link rel="stylesheet" href="css/validationmsg.css">


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
 <script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="css/allPage.css">
<link rel="stylesheet" href="css/error.css">
<link href="css/jquery.filer.css" rel="stylesheet">
<link href="css/jquery.filer-dragdropbox-theme.css"
	rel="stylesheet">
<title>New Employee</title>
    
    <!-- New Import -->
     <link href="http://cdn-na.infragistics.com/igniteui/2017.1/latest/css/themes/infragistics/infragistics.theme.css" rel="stylesheet" />
    <link href="http://cdn-na.infragistics.com/igniteui/2017.1/latest/css/structure/infragistics.css" rel="stylesheet" />

    <script src="http://ajax.aspnetcdn.com/ajax/modernizr/modernizr-2.8.3.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

 <!--    Ignite UI Required Combined JavaScript Files -->
    <script src="http://cdn-na.infragistics.com/igniteui/2017.1/latest/js/infragistics.core.js"></script>
    <script src="http://cdn-na.infragistics.com/igniteui/2017.1/latest/js/infragistics.lob.js"></script>

	    <!-- New Import -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="script/custom.js" type="text/javascript"></script>
<script src="script/jquery.filer.min.js" type="text/javascript"></script>
<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" /> -->
<!-- <script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script> -->


<script type="text/javascript" src="<c:url value="/script/jquery.validate.min.js"/>"></script>

<style>
table {
  border-collapse: separate;
 
  border-spacing: 30px 0;
}
td{
   padding: 5px 0;
}
#content {
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
				
				$(function(){
					$('.datepick').datepicker({
						viewMode: 'days',
						format : 'dd/mm/yy',
						showTodayButton : true,
						showOn: "button"
					});
				});
			});
</script>
<script>
$(document).ready(function() {
	$.validator.addMethod("pattern", function(value, element, regexpr) {  
		
	     return this.optional( element ) || regexpr.test(value);
	   }, "Please enter a valid value."); 
$("#addEmployee").validate({
	rules: {
		 "employeeName": {
			 required:true,
			pattern: /^[a-z A-Z]+$/,
			minlength:3,
			maxlength:30
		 },
            "employeeId":{
            	required:true,
            	pattern: /^[0-9-+]+$/,
            	minlength:8,
            	maxlength:8
            },
            "enterpriseId":{
            	required:true,
            	pattern:/^[a-zA-Z](?:.?[a-zA-Z]+)*$/,
            	minlength:3,
    	        maxlength:30
    	     
            },
           "level":{
        	   required:true,
        	   range: [5,12]
        	   
           },
           "roleName":{
        	   required:false,
        	   pattern:/^[a-zA-Z0-9]+$/,
        	   minlength:3,
        	   maxlength:30
           },
           
           "lanId":{
        	   required:false
           },
           
           "lcr":{
        	   required:false,
        	   pattern: /^[0-9]\d{0,9}(\.\d{1,2})?%?$/,
        	   maxlength:10
           },
           "technologyId":{
        	   required:true
           },
           "supervisorEnterpriseId":{
        	   required:true
           },
           "projectId":{
        	   required:false
           },
          
		
   		},
	messages:{
		
		"employeeName": {
			required: 'Employee Name is required.',
			
			maxlength: 'Employee Name should be maximum 30 characters.',
			pattern: 'Employee Name should be in alphabets'
		},
		"employeeId": {
					required: 'Employee Id is required.',
					pattern:'Employee Id should be in numbers.',
					minlength:'Employee Id should be minimum 8 digits.',
					maxlength: 'Employee Id should be maximum  8 digits.'
				
		},	
					
		"enterpriseId": {
			required: 'Enterprise Id is required',
			
			maxlength: 'Enterprise Id should be maximum 30 characters.',
			pattern: 'Enterprise Id should be in alphabets'
		},
   		
   		"level": {
			required: 'Career level should be provided',
			range: 'Your career level must be between 5 and 12.',
			
	    },
		"roleName":{
			required:'Project Role Name is required ',
			pattern:'Project Role should be in alphabets'
		},
		
		"lanId":{
			required:'LAN ID is required'
		},
		"lcr":{
			required:'LCR is required',
			pattern:'LCR should be in numbers',
			maxlength:'LCR should be maximum 10 digits'
		},
		 "technologyId":{
	   			required:'Please choose a technology.'
	   		   },
	   	"supervisorEnterpriseId":{
	   			required:'Please provide Supervisor name.'
	   		},
	   	"projectId":{
				required:'Please choose a project.'
			}
},
errorContainer: $('#errorContainer'),
errorLabelContainer: $('#errorContainer ul'),
wrapper: 'li'
   	
});
});	
</script>

 <script>
$(document).ready(function(){
	/* var roles = $.ajax({
		type : 'POST',
		url : 'getAllRoles.htm',
		datatype : "json",
		async : false
		}).responseText; */
	var roles = [
        { Name: "ADMIN" },
        { Name: "DEFAULT" },
        { Name: "LCR_VIEW" },
        { Name: "LCR_UPDATE"},
        { Name: "TIME_APPROVE" },
        { Name: "HUB_VIEW" },
        { Name: "HUB_UPDATE" },
        {	Name: "SHIFT_REPORT" },
        {Name: "IDEA_REVIEW" },
        {	Name: "STATISTICS" },
        {	Name: "MASTER_SETUP" },
        {	Name: "EMPLOYEE_DETAILS" }
        
    ];
    $("#multiSelectCombo").igCombo({
        width: 300,
        dataSource: roles,
        textKey: "Name",
        valueKey: "Name",
        multiSelection: {
            enabled: true
        },
        dropDownOrientation: "bottom"
    });
});
 /*    var roles = [
        { Name: "Administrator" },
        { Name: "Default" },
        { Name: "Supervisor" },
        { Name: "AM" }
    ]; */
        $(function () {
        /*     $("#multiSelectCombo").igCombo({
                width: 300,
                dataSource: roles,
                textKey: "Name",
                valueKey: "Name",
                multiSelection: {
                    enabled: true
                },
                dropDownOrientation: "bottom"
            }); */
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
							SUPID : $("#supervisorId").val(),
							level : $("#level").val()
						},
						success : function(data) {
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
		<center><h2><b> New Employee Register </b></h2></center>
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
					<form name="empform" action="addEmployee.htm" method="post" id="addEmployee">
						<table>
							<tr>
<td><label for="Employee Name">Employee Name<span style="color:red;">*</span></label></td>
<td><input class = "textBox" type="text" name="employeeName" required></td>
<td>&nbsp;</td>

</tr>
<tr class="break"><td colspan="2"></td></tr>
<tr>
<td><label for="employeeid">Employee ID<span style="color:red;">*</span></label></td>
<td><input class = "textBox" type="text" name="employeeId" required> </td>
<td>&nbsp;</td>

</tr>
	<tr class="break"><td colspan="2"></td></tr>
		<tr><td><label for="enterprise_id">Enterprise Id<span style="color:red;">*</span></label></td>
		<td><input class = "textBox" type="text" name="enterpriseId" ></td>
				<td>&nbsp;</td>
				
			</tr>	
				
				<tr class="break"><td colspan="2"></td></tr>
					<tr><td><label for="career level">Career Level<span style="color:red;" >*</span></label></td>
					<td>
					<select class = "textBox" name="level" id="level" required>
										<option value="">--Select a Level--</option>
  										<option value="12">12</option>
  										<option value="11">11</option>
  										<option value="10">10</option>
  										<option value="9">9</option>
  						  				<option value="8">8</option>
 										<option value="7">7</option>
 										<option value="6">6</option>
 										<option value="5">5</option>
									</select>
					
					</td><td>&nbsp;</td>
						
						</tr>
						<tr class="break"><td colspan="2"></td></tr>
							
							<tr><td><label for="Supervisor Name">Supervisor ID<span style="color:red;">*</span></td>
								<td><input type="text" id="supervisorId" name="supervisorEnterpriseId" class = "textBox" onkeyUp="populateSuperVisor(); "/></td>
				
								<td>&nbsp;</td>
								
								
								</tr>
								<tr class="break"><td colspan="2"></td></tr>
								
								<tr>								
								<td><label for="Role On Date">RoleOn Date<span style="color:red;">*</span></label>
								</td>								
								<td>
								<div class="form-group">
										<div class='input-group date '>
								<input type="text" id="roleOnDate" name="roleOnDate" class = "textBox datepick" />
								</div>
								</div>
								</td>	
								</tr>
								<tr class="break"><td colspan="2"></td></tr>	
								<tr><td><label for="Technology">Technology<span style="color:red;">*</span></td>
						          <td>							
									<select  class = "textBox" name="technologyId" required>
									<option value="">--select an option---</option>
 										<c:forEach items="${technologies}" var="technology">
											<option value="${technology.technologyId}">${technology.technologyName}</option>
										</c:forEach>
									</select>	
							      </td>
							
												
								</tr>
							
						<tr class="break"><td colspan="2"></td></tr>
						<tr><td><label for="Roles">Roles</label></td><td><div  name="roles" id = "multiSelectCombo"></div></td></tr>
							<tr class="break"><td colspan="2"></td></tr>	
										
					<tr class="break"><td colspan="2"></td></tr>	
				<tr align="center"><td colspan="2"><input class="btn btn-info dropdown-toggle" name="submit" type="submit" value="Assign Project" >&nbsp;&nbsp;<input class="btn btn-info dropdown-toggle" name="submit" type="submit" value="Save" >&nbsp;&nbsp;
				<a href="javascript:goBack(); " class="btn btn-info dropdown-toggle" >Cancel</a></td>
				</tr>				
					
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