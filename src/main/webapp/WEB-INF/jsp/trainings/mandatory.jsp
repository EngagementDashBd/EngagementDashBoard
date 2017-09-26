<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>Mandatory Training</title>
<link rel="stylesheet" href="css/Noto+Sans.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript">
	function reload() {
		window.location = "redirect.htm?pageName=myProfile";
	}

	function openHcsc() {
		window.location = "hcscMenu.htm";
	}
</script>
</head>
<body id="home">
	<div data-role="page">
		<div data-role="header" data-position="absolute">
			<hr>
			<div class="header-data">
				Mandatory Training<img onClick="reload()" src="images\home-65.png"
					height="50" width="50" align="right" style="cursor: pointer">
			</div>
			<hr>
			<p align="center" style="color: blue">The below mentioned
				trainings must be completed within the given time period.</p>
		</div>

		<div data-role="main" class="ui-content">

			<div class="c">
				<div>
					<div class="r">
						<a class="mouse" onclick="openHcsc()"><div>
								<img src="images/classroom.jpg" data-src="images/classroom.jpg" width="360"
									height="240">
							</div>
							<div>
								<p>HCSC trainings overview</p>
								<h1>HCSC University</h1>
							</div></a><br>
							<a href="https://mylearning.accenture.com/accenture/lang-en/management/LMS_ActDetails.asp?ActId=681721" target="_blank"><div>
								<img src="images/agile.png" data-src="images/agile.png" width="360"
									height="240">
							</div>
							<div>
								<p>Agile development process - 2 hour training</p>
								<h1>Introducing Agile Software Development</h1>
							</div></a>
					</div>
				</div>
				<div>
					<div class="r">
						<a href="https://mycertification.accenture.com/web/Dashboard?certificationId=627" target="_blank"><div>
								<img src="images/agile intro.png" data-src="images/agile intro.png"
									width="360" height="240">
							</div>
							<div>
								<p>Agile Delivery School</p>
								<h1>Agile Professional Certification</h1>
							</div></a>
							<a href="https://mylearning.accenture.com/myl-ui/learner/activityDetails?referrer=search&activityID=1219057&source=myLearning&refresh=1135.4008587588616" target="_blank"><div>
								<img src="images/devops-process.png" data-src="images/devops-process.png" width="360"
									height="240">
							</div>
							<div>
								<p>Tools, Technologies, and Infrastructures - 1.5 hours</p>
								<h1>DevOps Fundamentals</h1>
							</div></a>
					</div>
				</div>
				<div>
					<div class="r">
						<a href="https://newit.accenture.com/" target="_blank"><div>
								<img src="images/newit.png" data-src="images/newit.png"
									width="360" height="240">
							</div>
							<div>
								<p>Become a New IT Conversant</p>
								<h1>New IT</h1>
							</div></a><br>
							<a href="https://mylearning.accenture.com/accenture/lang-en/management/LMS_ActDetails.asp?UserMode=0&ActivityId=898923" target="_blank"><div>
								<img src="images/scrum.png" data-src="images/scrum.png" width="360"
									height="240">
							</div>
							<div>
								<p>Scrum development process - 2 hour training</p>
								<h1>Overview of Scrum Development Process</h1>
							</div></a>
					</div>
				</div>
			</div>
		</div>
		<div data-role="footer" data-position="fixed" align="center">
			<h1>
				<a href="https://accenture.com">@ Copyright 2017-2018 Accenture. All Rights Reserved. Accenture Confidential. For Internal Use only</a>
			</h1>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="script/Script.js"></script>
</body>
</html>