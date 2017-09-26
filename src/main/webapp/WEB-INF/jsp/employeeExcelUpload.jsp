<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Excel Upload</title>
</head>
<body>
<br>
<br>
<br>

	<form action="excelUpload.htm" method="post" enctype="multipart/form-data">
		<input name="excelfile" type="file">
		<br>
		<br>
		<input type="submit" value="Upload">
	</form>
	Refer the Template
	<a
										href="downloadTemplate.htm?templateName=Bulk Upload Template.xlsx"> <img
											src="css/images/downloadIcon.png" alt="download template"
											style="width: 30px; height: 28px; border: 0;">
									</a>
</body>
</html>