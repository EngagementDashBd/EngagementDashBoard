<%-- <%@include file = "adminTabs.jsp"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Idea Details</title>
<%-- <script type="text/javascript" src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script> --%>
<script type="text/javascript"
	src="<c:url value="/script/jquery.validate.min.js"/>"></script>
 <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css"/>
        
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>


<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="css/allPage.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>  -->
<link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
	<style>
            #popup{
                display: none;
                border: 1px solid black;
            }
            
            #popupprogress{
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
<c:if test="${code == 'success'}">
<script>
  alert("Progress Added Successfully");
 </script>
</c:if>
<c:if test="${code == 'failure'}">
<script>
  alert("Error Adding Progress");
  
</script>
</c:if>
<script>
	$(document).ready(function() {
		$('#ideaTable1').DataTable();
	});
</script>

<script type="text/javascript">
	function confirm_alert(node) {
		return confirm("Please click on OK to continue.");
	}
</script>
<c:if test="${progress == 'yes'}">
	<script>
	$( document ).ready(function() {
		$("#popupprogress").dialog({
	        width: 800,
	        height: 800,
	        title: "Progress Notes"
	        });
	 });
	</script>
</c:if>
</head>

<body>
<script>
$( document ).ready(function() {
    $(document).on("click", ".cell-which-triggers-popup", function(event){
    	var ideaID = $(event.target).parent().children('.ideaId').text();
    	var description = $(event.target).parent().children('.ideaDescription').text();
    	
       	
        showPopup(ideaID,description)    
    });

    function showPopup(ideaID,description){
        $("#popup").dialog({
            width: 800,
            height: 800,
            open: function(){
                $(this).find("#ideaId").val(ideaID) 
                $(this).find("#description").val(description) 
               
               
            }
        });
    }
});
</script>


	<div class="container" id="main">
		<center>
			<h1>
				<b>IDEA DETAILS</b>
			</h1>
		</center>
		<div id="popup" class="dialog">
            <!-- <a href="#close"><img src="http://bit.ly/1qAuZh3" alt="..." width="166" height="104" align="left"/></a> -->
            <form  action="addNote.htm" method="post">
	            <table align="center" >
	            <h1><label for="My Ideas">Idea Details</h1>
	            	<tr><td><label for="Idea Id">Idea Id</td><td><input type="text" id="ideaId" name="ideaId" class = "textBox"></td></tr>
	            	<tr class="break"><td colspan="2"></td></tr>
	            	<tr><td><label for="Idea Description">Idea Description</td><td><input type="text" id="description" class = "textBox"></td></tr>
	            	<tr class="break"><td colspan="2"></td></tr>
	            	<tr><td><label for="Comments">Progress</td><td><textarea rows="4" cols="50" name="progress" id="comments" class = "textBox"></textarea></td></tr>
	            	<tr class="break"><td colspan="2"></td></tr>
	            	<tr class="break"><td colspan="2"></td></tr>
	            	<tr align="center"><td><input type="submit" class="btn btn-info dropdown-toggle" value="Add Note"></td></tr>
	            </table>
            </form>         
        </div>
		
		<br> <br>
		  <div id="popupprogress" class="dialog">
            <!-- <a href="#close"><img src="http://bit.ly/1qAuZh3" alt="..." width="166" height="104" align="left"/></a> -->
            <table>
           
            <tr></tr>
            <c:if test="${ideaProgress != null}">
            <c:forEach items="${ideaProgress}" var="progress" varStatus="loop">
            
           <tr><td> DATE : <fmt:formatDate value="${progress.creationDate}" pattern="dd-MM-yyyy" /></td><td>&nbsp;&nbsp;</td><td> AUTHOR : <c:out value="${progress.enterpriseId}"></c:out></td></tr>
           <tr><td colspan="3"><c:out value="${progress.progress}"></c:out></td></tr>               
            </c:forEach>
            </c:if>
            </table>     
        </div>
       
		<div class="row" align="center">
			<div class="col-md-12">
				<div id="proData" align="center">
					<table class="display jqueryDataTable" id="ideaTable1">
						<thead>
							<tr>
								<th>Idea Id</th>
								<th>Idea Description</th>
								<th>Progress</th>
								<th>Add Progress Note</th>
								
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${ideasunderdevelopment}" var="ideas" varStatus="loop">
							
								<tr>
									<th class="ideaId"><c:out value="${ideas.ideaId}"></c:out></th>
									
									<td class="ideaDescription" ><c:out value="${ideas.ideaDescription}" /></td>
									<td class="displayProgressNote">
										<a href="getProgress.htm?ideaId=${ideas.ideaId}" >
  											View Progress
										</a>
								</td>
									<td class="cell-which-triggers-popup">Add Note</td>
								
									
								</tr>
							</c:forEach>

						</tbody>
					</table>


				</div>
			</div>
		</div>
	</div>




</body>
</html>