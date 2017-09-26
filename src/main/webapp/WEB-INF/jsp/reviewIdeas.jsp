<%-- <%@include file = "adminTabs.jsp"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            a{
    cursor: pointer;    
  }
  
  .input-disabled{background-color:#EBEBE4;border:1px solid #ABADB3;padding:2px 1px
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
<script>
	$(document).ready(function() {
		$('#ideaTable').DataTable();
	});
</script>

<script type="text/javascript">
	function confirm_alert(node) {
		return confirm("Please click on OK to continue.");
	}
</script>
</head>

<body>
<c:if test="${devcode == 'success'}">
		<script>
			alert("Developers Added Successfully");
		</script>
	</c:if>
	<c:if test="${devcode == 'failure'}">
		<script>
			alert("Error..... Please Try again !!!");
		</script>
	</c:if>

<script>
$( document ).ready(function() {
    $(document).on("click", ".cell-which-triggers-popup", function(event){
    	var ideaID = $(event.target).parent().children('.ideaId').text();
    	var description = $(event.target).parent().children('.ideaDescription').text();
    	var owner = $(event.target).parent().children('.owner').text();
    	var team = $(event.target).parent().children('.team').text();
       	
        showPopup(ideaID,description,owner,team)    
    });

    function showPopup(ideaID,description,owner,team){
        $("#popup").dialog({
            width: 800,
            height: 800,
            open: function(){
                $(this).find("#ideaId").val(ideaID) 
                $(this).find("#description").val(description) 
                $(this).find("#owner").val(owner) 
                $(this).find("#ideaId").attr('readonly', true);
                $(this).find("#description").attr('readonly', true);
                $(this).find("#owner").attr('readonly', true);
                $(this).find("#ideaId").addClass('input-disabled');
                $(this).find("#description").addClass('input-disabled');
                $(this).find("#owner").addClass('input-disabled');
            }
        });
    }
});
</script>
<script>
$( document ).ready(function() {
    $(document).on("click", ".addDeveloperPopUp", function(event){
    	var ideaID = $(event.target).parent().children('.ideaId').text();
    	var description = $(event.target).parent().children('.ideaDescription').text();     	
        showDeveloperPopup(ideaID,description)    
    });

    function showDeveloperPopup(ideaID,description){
        $("#developePopUp").dialog({
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

<script>
		
			function populateSuperVisor() {

			$("#developer").autocomplete({
				// appendTo: "#newSupervisor", 	
		
				source : function(request, response) {
				
					$.ajax({
						type : "POST",
						minLength : 1,
						url : "SeachSupervisor.htm",
						data : {
							SUPID : $("#developer").val()
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
	function getIdeaDeatails(value) {
		$('#ideaInDetail').load('getIdeaDetails.htm', {
			ideaId : value
		}, function() {

			$("#ideaInDetail").dialog({

				maxWidth : 1200,
				maxHeight : 1500,
				width : 850,
				height : 550,				
				title : 'Idea Details'
				
				

			});
			}

		);

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
	<div class="container" id="main">
		<center>
			<h1>
				<b>IDEA DETAILS</b>
			</h1>
		</center>
		<br> <br>
		 <div id="popup" class="dialog">
            <a href="#close"><img src="http://bit.ly/1qAuZh3" alt="..." width="166" height="104" align="left"/></a>
            <form id="review" action="reviewIdea.htm" method="post">
	            <table align="center">
	            <h1><label for="Idea Details">Idea Details</h1>
	            	<tr><td><label for="Idea Id">Idea Id</td><td><input type="text" id="ideaId" name="ideaId" class = "textBox" ></td></tr>
	            	<tr class="break"><td colspan="2"></td></tr>
	            	<tr><td><label for="Idea Description">Idea Description</td><td><input type="text" id="description" class = "textBox" ></td></tr>
	            	<tr class="break"><td colspan="2"></td></tr>
	            	<tr><td><label for="Idea Owner">Idea Owner</td><td><input type="text" id="owner" class = "textBox"></td></tr>
	            	<tr class="break"><td colspan="2"></td></tr>
	            	<tr><td><label for="Comments">Comments</td><td><textarea rows="4" cols="50" name="comments" id="comments" class = "textBox"></textarea></td></tr>
	            	<tr class="break"><td colspan="2"></td></tr>
	            	<tr class="break"><td colspan="2"></td></tr>
	            	<tr align="center"><td><input type="submit" class="btn btn-info dropdown-toggle" value="Approve" name="approveOrReject">&nbsp;&nbsp;<input type="submit" class="btn btn-info dropdown-toggle" value="Reject" name="approveOrReject">&nbsp;&nbsp;<input type="submit" class="btn btn-info dropdown-toggle" value="Hold" name="approveOrReject"></td></tr>
	            </table>
            </form>         
        </div>
      
		<div class="row" align="center">
			<div class="col-md-12">
				<div id="proData" align="center">
					<table class="display jqueryDataTable" id="ideaTable">
						<thead>
							<tr>
								<th>Idea Id</th>
								<!-- <th>Idea Title</th> -->
								<th>Idea Description</th>
								<th>Idea Owner</th>
								<th>Team</th>
								<th>Status</th>
								<th>Current Effort</th>
								<th>Planned Effort</th>
								<th>Effort Saving</th>
								<th>Business Saving</th>
								<sec:authorize access="hasRole('IDEA_REVIEW')">	
												
										<th>Approve/Reject</th>
								
											</sec:authorize>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${ideas}" var="ideas" varStatus="loop">
							
								<tr>
									<th class="ideaId"><a id="${ideas.ideaId}"  onclick="return  getIdeaDeatails(this.id);"><c:out value="${ideas.ideaId}"></c:out></a></th>
									<td class="ideaDescription"><c:out value="${ideas.ideaDescription}" /></td>
									<td class="owner"><c:out value="${ideas.enterpriseId}" /></td>
									<td class="team"><c:out value="${ideaAndTeam[ideas.ideaId]}" /></td>
									<td><c:out value="${ideaAndStatus[ideas.ideaId]}" /></td>
									<%-- 	<td class="IdeaTitle" ><c:out value="${ideas.IdeaTitle}" /></td> --%>
									<td class="currenteffort"><c:out value="${ideas.iCurrentEffort}" /></td>
									<td class="plannedeffort"><c:out value="${ideas.iPlannedEffort}" /></td>
									<td class="effortsaving"><c:out value="${ideas.iEffortSaving}" /></td>
									<td class="businessaving"><c:out value="${ideas.iBusinessSaving}" /></td>
									
									<c:if test="${page == 'myidea' and ideaAndStatus[ideas.ideaId] == 'Approved' }">
										<td align="center" class="addDeveloperPopUp">
									<a href="redirect.htm?pageName=addDevelopers&ideaId=${ideas.ideaId}&ideaDescription=${ideas.ideaDescription}">
  									<img src="css/images/add.jpg" alt="Add Developers" style="width:30px;height:28px;border:0;">
									</a>
							</td> 
									</c:if>
									<c:if test="${page == 'myidea' and ideaAndStatus[ideas.ideaId] == 'Approved' }">
										<td class="displayProgressNote">
										<a href="getProgress.htm?ideaId=${ideas.ideaId}&page=myIdea" >
  											View Progress
										</a>
								</td>
							
									</c:if>
									
									<sec:authorize access="hasRole('IDEA_REVIEW')">	
									<c:if test="${ideaAndStatus[ideas.ideaId] == 'Approved' }">
										<td>Review</td>
									</c:if>
									<c:if test="${ideaAndStatus[ideas.ideaId] != 'Approved' }">
										<td class="cell-which-triggers-popup">Review</td>
									</c:if>
									</sec:authorize>	
								</tr>
							</c:forEach>

						</tbody>
					
					</table>

			<a href="redirect.htm?pageName=navritiExcel" class="btn btn-info dropdown-toggle">Generate Report</a>
				</div>
			</div>
		</div>
		<div id="ideaInDetail"></div>
	</div>

  <div id="popupprogress" class="dialog">
             <a href="#close"><img src="http://bit.ly/1qAuZh3" alt="..." width="166" height="104" align="left"/></a> 
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


</body>
</html>