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
<title>Ideas</title>
    <!--  Jquery Validation-->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/jquery.validate.min.js"/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/additional-methods.js"></script>
<link rel="stylesheet" href="css/validationmsg.css">
<link rel="stylesheet" href="css/allPage.css"/>
<script src="script/validate.js"></script>
    
    <!-- New Import -->
     <link href="http://cdn-na.infragistics.com/igniteui/2017.1/latest/css/themes/infragistics/infragistics.theme.css" rel="stylesheet" />
    <link href="http://cdn-na.infragistics.com/igniteui/2017.1/latest/css/structure/infragistics.css" rel="stylesheet" />

    <script src="http://ajax.aspnetcdn.com/ajax/modernizr/modernizr-2.8.3.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
    <!--  Jquery Validation-->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/jquery.validate.min.js"/>"></script>

<link rel="stylesheet" href="css/validationmsg.css">
<link rel="stylesheet" href="css/allPage.css"/>
<script src="script/validate.js"></script>
    

 <!--    Ignite UI Required Combined JavaScript Files -->
    <script src="http://cdn-na.infragistics.com/igniteui/2017.1/latest/js/infragistics.core.js"></script>
    <script src="http://cdn-na.infragistics.com/igniteui/2017.1/latest/js/infragistics.lob.js"></script>

	    <!-- New Import -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="script/custom.js" type="text/javascript"></script>
<script src="script/jquery.filer.min.js" type="text/javascript"></script>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>





<script>
$(document).ready(function(){
	$("#addCF").click(function(){
		$("#customFields").append('<tr valign="top"><th scope="row"><label for="customFieldName">Custom Field</label></th><td><input type="text" class="code" id="customFieldName" name="customFieldName[]" value="" placeholder="Input Name" /> &nbsp; <input type="text" class="code" id="customFieldValue" name="customFieldValue[]" value="" placeholder="Input Value" /> &nbsp; <a href="javascript:void(0);" id="remCF">Remove</a></td></tr>');
		$("#remCF").on('click',function(){
			$(this).parent().parent().remove();
		});
	});
});
</script>


 <!-- <script>


  
 <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
  src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>   -->




<style>
table {
  border-collapse: separate;
 
  border-spacing: 30px 0;
}
td{
   padding: 5px 0;
}

</style>

<script>

function populatesupervisor(value)
{
	$("#"+ value).autocomplete({
		// appendTo: "#newSupervisor", 	
		
		source : function(request, response) {
		
			$.ajax({
				type : "POST",
				minLength : 1,
				url : "SeachSupervisor.htm",
				data : {
					SUPID : $("#"+value).val()
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
$(document).ready(function() {
	
	$.validator.addMethod("pattern", function(value, element, regexpr) {  
		
	     return this.optional( element ) || regexpr.test(value);
	   }, "Please enter a valid value."); 

	
	
	$("#ideaForm").validate({
	
		 rules: {
			 			 			 
			 "category" : {
					 required:true
					 },
					 
				"subCategory": {
						 required:true
						 },
				 "ideaDescription":{
		            	required:true,
		            	pattern: /^[a-z A-Z]+$/,
		            	maxlength:50
		            },
		         "iCurrentEffort": {
		        		required:true,
		        		maxlenght:6
		        	 
		         },
		         "iPlannedEffort": {
		        		required:true,
		        		maxlenght:6
		        	 
		         },
		         "iBusinessSaving": {
		        		required:true,
		        		maxlenght:6
		        	 
		         },
		         "iEffortSaving": {
		        		required:true,
		        		maxlenght:6
		        	 
		         },
		         "ideaTitle":{
		     		required:true,
	        		maxlenght:6
		         },
		         
		           
		          
				
		   		},
			messages:{
				
				
				
				"category": {
					required: 'Please choose a Category .'
					
				},
				
				"subCategory" : {
					required: 'Please choose a Sub-Category .'
					
				},
				
				"ideaDescription": {
					required: 'Idea Description is required.',
					maxlength: 'Idea Description should be maximum  50 characters.'
				},
				
				"iCurrentEffort": {
					required:  'Current Effort is required.',
					maxlength: 'Current Effort should be maximum  6 characters.'
		    	},
		    	
		    	"iPlannedEffort": {
					required: 'Planned Effort is required.',
					maxlength: 'Planned Effort should be maximum   6 characters.'
				},
				"iBusinessSaving": {
					required: 'Business Saving is required.',
					maxlength: 'Business Saving  should be maximum   6 characters.'
				},	
				"iEffortSaving"	: {
					required: 'Effort Saving is required.',
					maxlength: 'Effort Saving should be maximum   6 characters.'
				},		
				"ideaTitle"	: {
					required: 'Idea Title is required.',
					maxlength: 'Idea Title should be maximum   250 characters.'
				},		
				
				
				
				
			},
		 errorContainer: $('#errorContainer'),
	     errorLabelContainer: $('#errorContainer ul'),
	     wrapper: 'li'
});

});

</script>
<script type="text/javascript">
 function eSaving()  {
	
    var txtFirstNumberValue = document.getElementById('ceffort').value;
    var txtSecondNumberValue = document.getElementById('peffort').value;
    var result = parseInt(txtFirstNumberValue) - parseInt(txtSecondNumberValue);
    if (!isNaN(result)) {
       document.getElementById('esaving').value = result;
    }
}
</script>
<script type="text/javascript">
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

</script>
 

<script>
function deleteRow(r) {
	
	
	var i = r.parentNode.parentNode.rowIndex;
    document.getElementById("suTable").deleteRow(i);
    
}
</script>

<script>
function addrow()
{

	var index=document.getElementById("index").value; 
	
	$("#suTable").append('<tr><td><label>Additional Approver</label> </td><td><input type="text" class="textbox"  id="'+index+'"  onkeyup="return populatesupervisor(this.id);" ></td></tr>');
	var  sname=document.getElementById("sName").value;
	index++;
	document.getElementById("index").value=index;
		
}



</script>
<script type="text/javascript">

function appendSupervisor()
{

	
	var index=document.getElementById("index").value;
	
	var  sname=document.getElementById("sName").value;

	var aname;
	if(index == 0)
	{
		alert(sname)
		document.getElementById("AppendName").value=sname;
	}
		
	for(i =0;i<=index;i++)
	{
		var TSName=document.getElementById(i).value;
		if(TSName!= null)
		{
		sname+= ','+ TSName;
		}
		document.getElementById("AppendName").value=sname;
	}

	
	

	
}
</script>

<script type="text/javascript">
function goBack() {
    window.history.back();
}
</script>
</head>
<body>


	<div class="container" id="main">
		<center><h2><b> New Idea </b></h2></center>
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
					<form name="ideaForm" action="addIdea.htm" method="post" id="ideaForm">
					   <input type="hidden"  name="index" value="0" id="index"/>
					    
					<input type="hidden" name="enterpriseId" value="${resource.enterpriseId}"/>
						<table id="table1">
								<tr class="break"><td colspan="2"></td></tr>
		<tr><td><label for="enterprise_id">Enterprise Id<span style="color:red;">*</span></label></td>
		<td><input class = "textBox" type="text" value="${resource.enterpriseId}" disabled></td>
		
								
			</tr>	
				
				<tr class="break"><td colspan="2"></td></tr>
				<tr><td><label for="ideaTitle">Idea Title<span style="color:red;">*</span></label></td>
		<td><input class = "textBox" type="text" name="ideaTitle"></td>
								
			</tr>	
				
				<tr class="break"><td colspan="2"></td></tr>
					<tr><td><label for="Idea Description">Idea Description<span style="color:red;" >*</span></label></td>
					<td>
					<textarea rows="4" cols="50" name="ideaDescription"></textarea>
					
					
					</td><td>&nbsp;</td>
						
						</tr>
						<tr class="break"><td colspan="2"></td></tr>
							
									<tr class="break"><td colspan="2"></td></tr>
								<tr><td><label for="Category">Category<span style="color:red;">*</span></td>
						          <td>							
									<select name="category">
										<option value="">--Select a Category</option>
										<option value="Accenture Internal">Accenture Internal</option>
										<option value="Client Centric">Client Centric</option>
									</select>
							      </td>
							
												
								</tr>
								<tr><td><label for="subCategory">Sub Category<span style="color:red;">*</span></td>
						          <td>							
									<select name="subCategory">
										<option value="">--Select a Sub Category</option>
										<option value="Automation">Technical Automation</option>
										<option value="Automation">Business Automation</option>
										<option value="Pain Point">Pain Point</option>
										<option value="Process Improvement">Process Automation</option>
									</select>
							      </td>
							
												
								</tr>
					
			</table>
			
			<table id="suTable">
			
	
			<tr>
			<td><label for="Supervisor Name">Supervisor Name<span style="color:red;">*</span></td>
								<td><input type="text" id="supervisorId" name="supervisorEnterpriseId" class = "textBox"  value="${Supervisorname}"/><a href="#" id="bottle" onclick="addrow();return false;"><img src="css/images/AddSupervisor.png"    alt="Add Advisor"   title="Add Supervisor or Approver" style="width:30px;height:28px;border:0;"></a></td>
				</tr> 
						
						
			</table>			
			 <input type="hidden"  name="supervisorName" value="${Supervisorname}" id="sName"/>
			  <input type="hidden"  name="approverAndSuperVisors"  value="abc" id="AppendName"/>
				
			<table>		
					<tr><td><label for="ceffort">Current Effort(Hours)<span style="color:red;">*</span></label></td>
		<td><input class = "textBox" type="text" id="ceffort" name="iCurrentEffort" onkeypress="return isNumber(event)"  onkeyup="eSaving();" ></td>
								
			</tr>	
			
				
				<tr class="break"><td colspan="2"></td></tr>
					<tr><td><label for="peffort">Planned Effort(Hours)<span style="color:red;">*</span></label></td>
		<td><input class = "textBox" type="text" name="iPlannedEffort" id="peffort"  onkeypress="return isNumber(event)"  onkeyup="eSaving();"></td>
								
			</tr>	
				
				<tr class="break"><td colspan="2"></td></tr>
				
					<tr><td><label for="esaving">Effort Saving(Hours)</label></td>
		<td><input class = "textBox" type="text" id="esaving" name="iEffortSaving"></td>
								
			</tr>	
				
				<tr class="break"><td colspan="2"></td></tr>
					<tr><td><label for="enterprise_id">Business Saving($)</label></td>
		<td><input class = "textBox" type="text" name="iBusinessSaving"></td>
								
			</tr>	
				
				<tr class="break"><td colspan="2"></td></tr>
										
					<tr class="break"><td colspan="2"></td></tr>	
				<tr align="center"><td colspan="2"><input class="btn btn-info dropdown-toggle" onclick="appendSupervisor(); "  name="submit" type="submit" value="Submit" >&nbsp;&nbsp;
				<input type="button" class="btn btn-info dropdown-toggle" value="Cancel" onClick="javascript:goBack(); "/>
				<!-- <a href="javascript:goBack(); " class="btn btn-info dropdown-toggle" >Cancel</a></td> -->
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