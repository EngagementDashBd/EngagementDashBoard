<%-- <%@include file = "adminTabs.jsp"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Idea Details</title>
<%-- <script type="text/javascript" src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script> --%>
<script type="text/javascript"
	src="<c:url value="/script/jquery.validate.min.js"/>"></script>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">      
       <!--  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script> -->
<link rel="stylesheet" href="/resources/demos/style.css">


<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->

<link rel="stylesheet" href="css/allPage.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>  -->
<link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<style>
            #popup{
                display: none;
                border: 1px solid black;
            }

            .cell-which-triggers-popup{
                cursor: pointer;    
            }

            .cell-which-triggers-popup:hover{
                background-color: #8CE3F7;    
            }
             #developePopUp{
                display: none;
                border: 1px solid black;
            }

            .addDeveloperPopUp{
                cursor: pointer;    
            }

            .addDeveloperPopUp:hover{
                background-color: #8CE3F7;    
            }
        </style>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<c:if test="${reviewCode == 'success'}">
<script>
  alert("Review Added Successfully");
  document.getElementById('popup').close();
</script>
</c:if>
<c:if test="${reviewCode == 'failure'}">
<script>
  alert("Error Adding Review");
  document.getElementById('popup').close();
  
</script>
</c:if>
<script type="text/javascript">
function goBack() {
    window.history.back();
}
</script>
</head>

<body>

<script>
$(document).ready(function(){
	$(document).on('keyup', "input[type='text']",function(){
    	
    	
    	 populate(this.id) 
   

		});
});
	
	</script>
	<script>
	function populate(id)
	{
		
		$("#"+ id).autocomplete({
			// appendTo: "#newSupervisor", 	
			
			source : function(request, response) {
			
				$.ajax({
					type : "POST",
					minLength : 1,
					url : "SeachSupervisor.htm",
					data : {
						SUPID : $("#"+id).val()
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

function addDeveloperTextBox()
{
	
	
	var table=document.getElementById("developerList");
	var row=table.insertRow(table.rows.length);
    var cell1=row.insertCell(0);
    var index = document.getElementById("index").value;	
    var x = "addButton" + index;
    document.getElementById(x).style.visibility = "hidden";
    var cell2 =   row.insertCell(1);
    var t1=document.createElement("input");
    t1.id = "developer"+index;
    t1.name = "developer"+index;
    t1.className = "textBox";
    t1.type = "text";
    document.getElementById("index").value = Number(index) + Number(1); 
    cell2.appendChild(t1);
     var cell3 =   row.insertCell(2);
     var cell4 = row.insertCell(3);
     var a = document.createElement('a');  
     index++;
    a.id = "addButton"+ index;
    a.className = "btn btn-info dropdown-toggle";
    var linkText = document.createTextNode("Add More");
    a.appendChild(linkText);
    a.title = "Add Developers";
    a.href = "javascript:addDeveloperTextBox()";
    cell4.appendChild(a);

  
	
}
</script> 

	<div class="container" id="main">
		<center>
			  <h1>Add Developers</h1>
		</center>
		<br> <br>
		<div>
        	 <a href="#close"><img src="http://bit.ly/1qAuZh3" alt="..." width="166" height="104" align="left"/></a>
            <form id="adddeveloper" action="addDeveloper.htm">
            <input type="hidden" name="index" value="1" id="index"/>
            <input type="hidden"  name="ideaId" value="${ideaId}"/>
	            <table align="center" id="developerList" class="developerList">
	          
	            	
	            	<tr class="break"><td colspan="2"></td></tr>
	            	<tr><td><label for="Idea Description">Idea Description</td><td><input type="text" id="description" name="ideaDescription" value="${ideaDescription}" class = "textBox" disabled></td></tr>
	            	<tr class="break"><td colspan="2"></td></tr>
	            	<tr><td><label for="Idea Developers">Idea Developers</td><td><input type="text" id="developer0" name="developer0" class = "textBox" /></td>
	            	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            	<td align="center">
						<a id="addButton1" href="javascript:addDeveloperTextBox();" class="btn btn-info dropdown-toggle">Add More</a>
					</td> </tr>
	            	
	            	
	            </table>
	            <br>
	            <br>
	            <center><input type="submit" class="btn btn-info dropdown-toggle" value="Add" name="addDevelopers">&nbsp;&nbsp;<a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></center>
            </form>  
        </div>
		
	</div>




</body>
</html>