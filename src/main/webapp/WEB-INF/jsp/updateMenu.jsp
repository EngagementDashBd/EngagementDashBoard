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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 <script type="text/javascript" src="http://davidstutz.github.io/bootstrap-multiselect/dist/js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="http://davidstutz.github.io/bootstrap-multiselect/dist/css/bootstrap-multiselect.css" type="text/css" />

<!-- <link rel="stylesheet" href="css/allPage.css">
 <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://davidstutz.github.io/bootstrap-multiselect/dist/js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="http://davidstutz.github.io/bootstrap-multiselect/dist/css/bootstrap-multiselect.css" type="text/css" /> -->

<script type="text/javascript">
function goBack() {
    window.history.back();
}
</script>
 <script type="text/javascript">
        $(document).ready(function () {
            $('#roleId').multiselect({
                includeSelectAllOption: true,
                enableCaseInsensitiveFiltering: true,
                enableFiltering: true,
                maxHeight: 200
            });
        });
        
        function GetSelectedCountries() {
            var selectedRoles = "";
            $("#roleId option:selected").each(function () {
            	selectedRoles += $(this).val() + ",";
            });
            if (selectedRoles) {
            	$("#roleList").val(selectedRoles);
            	alert($("#roleList").val());
            }
            else {
                alert("Please select at least one country");
            };
        }
        function selectValues(menuList,selectList)
        {
             $.each(menuList, function(_, defaultVal){
                 var found = false;
                 $.each(selectList, function(){
                     if(this == defaultVal){
                          $('#roleId option[value=' + this + ']').attr('selected', true);
                     }
                 });

                 
             });
        }
</script>
</head>
<body onLoad="selectValues(${roleList},${rolesOfMenu });">
<div class="container" id="main">
		<br>
		<br>
		<div class="row">
			<center><h2><b> UPDATE MENU DETAILS</b></h2></center>
			<div class="col-md-12">
			<br>
				<br>
				<br>
				<br>
			
				<div id="content">
				<br>
				<br>
				<form action="updateMenu.htm">
						<input type="hidden" name="id" value="${ menu.id}">
						<table>							
							<tr><td><label>Menu :</label></td><td><input id = "textBox" type="text" name="name" value="${ menu.name}"></td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr><td><label>Parent Id:</label></td><td>
							 <input id = "textBox" type="text" name="parentId" value="${menu.parentId}"/>
							</td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr><td><label>URL:</label></td><td>
							<input id = "textBox" type="text" name="url" value="${menu.url}"/>
							</td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr><td><label for="Roles">Roles<span style="color:red;">*</span></td>
						          <td>							
									<select  class = "textBox" name="roles" id="roleId" multiple="multiple">
									<option value="">--select an option---</option>
 										<c:forEach items="${roles}" var="roles">
											<option value="${roles.id}">${roles.name}</option>
										</c:forEach>
									</select>	
							      </td>
							
												
								</tr>
							<tr class="break"><td colspan="2"></td></tr>									
							<tr align="center"><td ><Button class="btn btn-info dropdown-toggle" type="submit">Update details</Button></td><td><a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></td></tr>
						</table>	
						<input type="hidden" name="roleList" id="roleList">				
					</form>
				

				</div>
				<br>
</body>
</html>