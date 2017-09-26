<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
 <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="css/allPage.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 <script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>


<script>

$(document).ready(function(){
    $("#attachmentTable").DataTable();
});
</script>
<script>
	$(document).ready(function(){
		 $("button").click(function() {
			 var fileName = $("#fileName").val();
			 var fileType = $("#fileType").val();
			 var createdBy = $("#createdBy").val();
			 var startDate = $("#startDate").val();
			 var endDate = $("#endDate").val();
			$.ajax({
				url : "searchAttachment.htm",
				dataType : 'html',
				data : {
					fileName : fileName,
					fileType : fileType,
					createdBy : createdBy,
					startDate : startDate,
					endDate : endDate
				},
				type : "POST",
				success : function(data) {
					var result = $('<div />').append(data).find('#fileList').html();
	        		$('#fileList').html(result);
	        		$("#attachmentTable").DataTable();
				},
				 error: function (jqXHR, textStatus, errorThrown) {
					 alert(textStatus)
				 }
			});
			 
		 });
	});
</script>
<script>
	$(document).ready(function() {

		$(function() {
			$('.datepik').datepicker({
				viewMode : 'days',
				format : 'mm/dd/yyyy',
				showTodayButton : true,
				showOn : "button"

			});
		});
	});
</script>

</head>
<body>
<h2>
	<b>Search For a File</b>
</h2>
<br><br>
<table>
<tr><td>File Name</td><td><input type = "text" class = "textBox" name = "fileName" id = "fileName"></td></tr>
<tr class="break"><td colspan="2"></td></tr>
<tr><td>File Type</td><td><input type = "text" class = "textBox" name = "fileType" id = "fileType"></td></tr>
<tr class="break"><td colspan="2"></td></tr>
<tr><td>Author</td><td><input type = "text" class = "textBox" name = "createdBy" id = "createdBy"></td></tr>
<tr class="break"><td colspan="2"></td></tr>
<tr><td>Upload date between </td><td><input type = "text" class = " textBox datepik" name = "startDate" id = "startDate">
and<input type = "text" name = "endDate" class = "textBox datepik" id = "endDate"></td></tr>
<tr class="break"><td colspan="2"></td></tr>
<tr><td><button class="btn btn-info dropdown-toggle" > Search</button></td></tr>
</table>
<br>
<br>
<br>
<div id="fileList">
<!-- class="display jqueryDataTable" -->
<table id = "attachmentTable" class="display jqueryDataTable">
	<thead>
		<tr>
			<th>#</th>
			<th>File Name</th>
			<th>Created By</th>
			<th>Download</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${attachmentData}" var = "attachment" varStatus = "loop">
		<tr>
			<td scope="row"><c:out value = "${loop.count }"></c:out></td>
			<td><c:out value = "${attachment.fileName}"/></td>
			<td><c:out value = "${attachment.createdBy}"/></td>
			<td><a href="downloadAttachment.htm?fileName=${attachment.fileName}" onclick="return confirm_alert(this);">
  					<img src="css/images/downloadIcon.png" style="width:30px;height:28px;border:0;">
				</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>