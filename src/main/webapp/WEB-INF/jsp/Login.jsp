<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Google Fonts -->
	<!-- <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>
 -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Custom Stylesheet -->
	<link rel="stylesheet" href="css/styles.css"><!-- 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> -->
<title>Login</title>
<script>
function lowerCase() {
    var str =  document.getElementById("enterprise").value;
    var res = str.toLowerCase();  
    document.getElementById("enterprise").value = res;
}
</script>
<script>
function preventBack(){window.history.forward();}
setTimeout("preventBack()", 0);
window.onunload=function(){null};
</script>
</head>
<body onload = "preventBack();">
<% String enterpriseId=(String)request.getParameter("enterpriseId"); %>
<c:if test="${code == 'success'}">
<script>
  alert("Signup Successful..... Login to Continue....");
</script>
</c:if>
<c:if test="${loginCode == 'failure'}">
<script>
  alert("Password Incorrect..... Please Try again !!!");
</script>
</c:if>
	<form action="<c:url value='j_spring_security_check'/>" method="post"
>
	<input type = "hidden" value = "<%= enterpriseId%>" id = "enterpriseId" name = "enterpriseId" >
	<input type = "hidden" value = "login" id = "flag" name = "flag">
	<div class="container">
		<div class="top">
			<h1 id="title" class="hidden"><span id="logo">Attendance Tool</span></h1>
		</div>
		<div class="login-box animated fadeInUp">
			<div class="box-header">
				<h2>Log In</h2>
			</div>
			<label for="username">EnterpriseId</label>
			<br/>
			<input type="text"  id = "enterprise" name = "enterprise" onBlur="lowerCase()">
			<br/>
			<input type="password" id = "password" name = password placeholder = "Enter Password">
			<br/>
			<button type="submit">Login</button>
		</div>
			<div style="font-size:12px; color:red; font-weight:bold">
							
					<c:if test="${not empty status}">						
							${status}
					</c:if>
					<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
								
        				Your login attempt was not successful due to <br />
						<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
     
					</c:if>
				</div>
		
	</div>
	</form>
</body>
<script>
	$(document).ready(function () {
    	$('#logo').addClass('animated fadeInDown');
    	$("input:text:visible:first").focus();
	});
	$('#username').focus(function() {
		$('label[for="username"]').addClass('selected');
	});
	$('#username').blur(function() {
		$('label[for="username"]').removeClass('selected');
	});
	$('#password').focus(function() {
		$('label[for="password"]').addClass('selected');
	});
	$('#password').blur(function() {
		$('label[for="password"]').removeClass('selected');
	});
</script>
</html>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<h1>Spring Security Custom Login Form (XML)</h1>

	<div id="login-box">

		<h2>Login with Username and Password</h2>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
		  action="<c:url value='j_spring_security_check' />" method='POST'>

		  <table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='enterprise' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
		  </table>

		

		</form>
	</div>

</body>
</html> --%>