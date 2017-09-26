<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>::HCSC UNIVERSITY::</title>
<link rel="stylesheet" href="css/Noto+Sans.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript">
	function openBcp() {
		window
				.open(
						"bcp.htm",
						'Business-Continuity-Plan',
						config = 'height=800, width=1100, left=40, top=40, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, directories=no, status=no');
	}
	function openContinuous() {
		window
				.open(
						"navritiMenu.htm",
						'Navriti',
						config = 'height=800, width=1100, left=40, top=40, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, directories=no, status=no');
	}
	function openHcsc() {
		window
				.open(
						"hcsc.htm",
						'HCSC',
						config = 'height=800, width=1100, left=40, top=40, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, directories=no, status=no');
	}
	function openHealth() {
		window
				.open(
						"healthOverviewMenu.htm",
						'US-Health',
						config = 'height=800, width=1100, left=40, top=40, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, directories=no, status=no');
	}
	function openInfoSec() {
		window
				.open(
						"informationsecurity.htm",
						'Info-Security',
						config = 'height=800, width=1100, left=40, top=40, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, directories=no, status=no');
	}
	function openOrg() {
		window
				.open(
						"organization.htm",
						'Organization',
						config = 'height=800, width=1100, left=40, top=40, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, directories=no, status=no');
	}
	function openTech() {
		window
				.open(
						"technology.htm",
						'Technology',
						config = 'height=800, width=1100, left=40, top=40, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, directories=no, status=no');
	}
</script>
<script>
	function goback() {
		window.location = "mandatory.htm";
	}

	function learningDashboard() {
		window.location = "learningDashboard.htm";
	}
</script>
</head>
<body id="home">
	<div data-role="page">
		<div data-role="header" data-position="absolute">
			<hr>
			<div class="header-data">
				HCSC UNIVERSITY&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
					onClick="goback()" src="images\go-backpng.png" height="50"
					width="80" align="right" style="cursor: pointer">
				<button type="button" onclick="learningDashboard()"
					style="float: left; border: 2px solid #008CBA;">HCSC
					Learning Dashboard</button>
			</div>
			<hr>
			<p align="center" style="color: red">NOTE: Every slide has been
				associated with TIMER, Thus only after certain amount of time the
				user will be able to change the slide.</p>
		</div>

		<div data-role="main" class="ui-content">

			<div class="c">
				<div>
					<div class="r">
						<a class="mouse" onclick="openBcp()"><div>
								<img src="images/BCP.jpg" data-src="images/BCP.jpg" width="360"
									height="240" data-position="absolute">
							</div>
							<div>
								<p>BCP</p>
								<h1>Business Continuity Plan</h1>
							</div></a> <a class="mouse" onclick="openContinuous()"><div>
								<img src="images/conti.jpg" data-src="images/conti.jpg"
									width="360" height="240">
							</div>
							<div>
								<p>Continuous Improvement</p>
								<h1>Navriti Framework &#038; Innovation at a Glance</h1>
							</div></a>
					</div>
				</div>
				<div>
					<div class="r">
						<a class="mouse" onclick="openHcsc()"><div>
								<img src="images/ovr.jpg" data-src="images/ovr.jpg" width="360"
									height="240">
							</div>
							<div>
								<p>HCSC Overview</p>
								<h1>HCSC at a Glance</h1>
							</div></a> <a class="mouse" onclick="openHealth()"><div>
								<img src="images/hlth.jpg" data-src="images/hlth.jpg"
									width="360" height="240">
							</div>
							<div>
								<p>Health Overview</p>
								<h1>Health Care Training</h1>
							</div></a> <a class="mouse" onclick="openTech()"><div>
								<img src="images/tech.jpg" data-src="images/tech.jpg"
									width="360" height="240">
							</div>
							<div>
								<p>Technology Stack</p>
								<h1>Multi-Skilling</h1>
							</div></a>
					</div>
				</div>
				<div>
					<div class="r">
						<a class="mouse" onclick="openInfoSec()"><div>
								<img src="images/infosec.jpg" data-src="images/infosec.jpg"
									width="360" height="240">
							</div>
							<div>
								<p>Information Security</p>
								<h1>Client Data Protection</h1>
							</div></a> <a class="mouse" onclick="openOrg()"><div>
								<img src="images/org.jpg" data-src="images/org.jpg" width="360"
									height="240">
							</div>
							<div>
								<p>Organization Chart</p>
								<h1>HCSC Organization Chart Key Contacts</h1>
							</div></a>
					</div>
				</div>
				<!-- <div>
					<div class="r">
						<a class="mouse" onclick="openTech()"><div>
								<img src="images/tech.jpg" data-src="images/tech.jpg"
									width="360" height="240">
							</div>
							<div>
								<p>Technology Stack</p>
								<h1>Multi-Skilling</h1>
							</div></a> 
						<a href="attendanceTracker.htm" target=_blank><div>
								<img src="images/attendance.PNG"
									data-src="images/attendance.PNG" width="360" height="240">
							</div>
							<div>
								<p>Attendance-Tracker</p>
								<h1>Track Shift Details of Employees</h1>
							</div></a>

					</div>
				</div> -->
			</div>
		</div>
		<div data-role="footer" data-position="fixed">
			<h1>
				<a href="https://accenture.com">@ Copyright 2017-2018 Accenture.
					All Rights Reserved. Accenture Confidential. For Internal Use only</a>
			</h1>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="script/Script.js"></script>
</body>
</html>