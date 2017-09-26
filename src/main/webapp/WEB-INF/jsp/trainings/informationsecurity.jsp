<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Information Security</title>
<link rel="stylesheet" href="css/reveal.css" media="all">
<link rel="stylesheet" href="css/theme/moon.css" id="theme">
<script src="script/head.min.js"></script>
<script src="script/reveal.js"></script>
<script src="script/easytimer.js" type="text/javascript"></script>
<script src="script/easytimer.min.js" type="text/javascript"></script>
<script src="script/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="script/jquery-ui-1.10.2.custom.min.js"
	type="text/javascript"></script>
</head>
<script>
	function timer() {
		var timer = new Timer();
		timer.start({
			countdown : true,
			startValues : {
				seconds : 5
			}
		});
		$('#countdownExample .values').html(timer.getTimeValues().toString());
		timer.addEventListener('secondsUpdated', function(e) {
			$('#countdownExample .values').html(
					timer.getTimeValues().toString());
		});
		timer
				.addEventListener(
						'targetAchieved',
						function(e) {
							Reveal.configure({
								controls : true,
								keyboard : true,
							});
						});
	}

	function timeExtension() {
		var timer1 = new Timer();
		timer1.start({
			countdown : true,
			startValues : {
				seconds : 30
			}
		});
		$('#countdownExample .values').html(timer1.getTimeValues().toString());
		timer1.addEventListener('secondsUpdated', function(e) {
			$('#countdownExample .values').html(
					timer1.getTimeValues().toString());
		});
		timer1
				.addEventListener(
						'targetAchieved',
						function(e) {
							Reveal.configure({
								controls : true,
								keyboard : true,
							});
						});
	}
</script>
<script>
	
	var output = "";

	$(document)
			.ready(
					function() {

						$
								.ajax({

									type : 'GET',
									url : 'getSlide.htm?topicId=5',
									dataType : "json",
									success : function(data) {
										output = data;
										if (output == 1) {
											timer();
										} else {
											if (confirm("Would you like to Resume from where you left?!") == true) {
												window.location = "#/slide"
														+ output;
												timeExtension();
											} else {
												timer()
											}
										}

									},
									error : function(request, status,
											errorThrown) {
										alert(errorThrown);
									}

								});
					});
</script>
<body>
	<div class="reveal">
		<!-- <div id="countdownExample">
			<div style="font-size: 20px" align="left" class="values"></div>
		</div> -->
		<div class="slides" align="center">
			<section id="slide1">
				<img src="images/is/Slide1.PNG">
			</section>
			<section id="slide2">
				<img src="images/is/Slide2.PNG">
			</section>
			<section id="slide3">
				<img src="images/is/Slide3.PNG">
			</section>
			<section id="slide4">
				<img src="images/is/Slide4.PNG">
			</section>
			<section id="slide5">
				<img src="images/is/Slide5.PNG">
			</section>
			<section id="slide6">
				<table border="1" style="font-size: 50%">
					<tr>
						<th colspan="3" align="center"
							style="color: white; font-size: 60px">CDP Controls</th>
					</tr>
					<tr>
						<th colspan="3" align="center" style="color: white">Accenture
							Client Data Protection requires the following controls:</th>
					</tr>
					<tr>
						<td>Accountability</td>
						<td>Change Management</td>
						<td>Legal/Contractual</td>
					</tr>
					<tr>
						<td>Delivery Locations</td>
						<td>Physical Security</td>
						<td>Access Control</td>
					</tr>
					<tr>
						<td>Approved Devices</td>
						<td>Encryption</td>
						<td>Transmission of Data</td>
					</tr>
					<tr>
						<td>Training</td>
						<td>Environment Controls</td>
						<td>Data Disposal</td>
					</tr>
					<tr>
						<td>Reuse of Work Products</td>
						<td>Security Incident Reporting</td>
						<td>Hosting</td>
					</tr>
					<tr>
						<td>Secure Application Development</td>
						<td colspan="2">Movement of People between Engagements
							(Roll-on/Roll-off)</td>
					</tr>
				</table>
				<p>Kindly Visit the link for more info:</p>
				<a
					href="https://in.accenture.com/protectingaccenture/client-data-protection/establish-cdp-plan/establish-controls/" target="_blank">https://in.accenture.com/protectingaccenture/client-data-protection/establish-cdp-plan/establish-controls/</a>
				<!-- <img src="images/is/Slide6.PNG"> -->
			</section>
			<section id="slide7">
				<img src="images/is/Slide7.PNG">
			</section>
			<section id="slide8">
				<img src="images/is/Slide8.PNG">
			</section>
			<section id="slide9">
				<img src="images/is/Slide9.PNG">
			</section>
			<section id="slide10">
				<img src="images/is/Slide10.PNG">
			</section>
			<section id="slide11">
				<img src="images/is/Slide11.PNG">
			</section>
			<section id="slide12">
				<img src="images/is/Slide12.PNG">
			</section>
			<section id="slide13">
				<img src="images/is/Slide13.PNG">
			</section>
			<section id="slide14">
				<img src="images/is/Slide14.PNG">
			</section>
			<section id="slide15">
				<img src="images/is/Slide15.PNG">
			</section>
			<section id="slide16">
				<img src="images/is/Slide16.PNG">
			</section>
			<section id="slide17">
				<img src="images/is/Slide17.PNG">
			</section>
			<section id="slide18">
				<img src="images/is/Slide18.PNG">
			</section>
			<section id="slide19">
				<img src="images/is/Slide19.PNG">
			</section>
			<section id="slide20">
				<h3 style="font-color:white">Appendix: Links and Contacts</h3>
				<ul>
					<li><a href="https://sites.accenture.com/publishing/ClientDataProtection" target="_blank">Client Data Protection website</a></li>
					<li><a href="https://sites.accenture.com/publishing/ClientDataProtection/RiskAssessment" target="_blank">CDP V4 Risk Assessment/Tool</a></li>
					<li><a href="https://sites.accenture.com/publishing/ClientDataProtection/ContractingandNegotiating" target="_blank">Contracting and Negotiating Guidance</a></li>
					<li><a href=" https://sites.accenture.com/publishing/ClientDataProtection/Controls" target="_blank">CDP V4 Controls: supporting details</a></li>
					<!-- <li><a href="https://sites.accenture.com/publishing/ClientDataProtection/RiskAssessment" target="_blank">Example CDP Plan:</a></li> -->
					<li><a href="https://ts.accenture.com/sites/Client_Data_Protection_Team_Site/Training/Mock%20CDP%20Plan/CDP%20V5%20-%20Mockup%20CDP%20Plan.zip" target="_blank">Example CDP Plan:</a></li>
					<li><a href="https://sites.accenture.com/publishing/ClientDataProtection/Documents/CDPv5_QuickReferenceGuide.zip" target="_blank">CDP Quick Reference Card</a></li>
					<!-- <li><a href="https://clientdataprotection@accenture.com">Email for any other questions</a></li> -->
				</ul>
					<p align="center">Email clientdataprotection@accenture.com for any other questions</p>
				<!-- <img src="images/is/Slide20.PNG"> -->
			</section>
			<section id="slide21">
				<img src="images/is/Slide21.PNG">
			</section>
			<section id="slide22">
				<img src="images/is/Slide22.PNG">
			</section>
			<section id="slide23">
				<img src="images/is/Slide23.PNG">
			</section>
			<section id="slide24">
				<h3 align="center">Thank you, Course Completed</h3>
				<p align="center">You can close the window now.</p>
			</section>
		</div>
	</div>
	<script>
var horzSlideCnt = document.querySelectorAll( '.reveal .slides>section' ).length;
	
	Reveal.initialize({
			center : true,
			controls : false,
			keyboard : false,
			progress : false,
			mouseWheel : false,
			transition : 'convex',
			width: 960,
            height: 750, 
		});
		
		Reveal.addEventListener('slidechanged', function(slidechanged) {
			// event.previousSlide, event.currentSlide, event.indexh, event.indexv
				Reveal.configure({
				controls : false,
				keyboard : false,
			});
			timeExtension();
			
			slideNumber = (slidechanged.indexh);
			
			$.ajax({

				type : 'POST',
				url : 'updateSlide.htm?topicId=5&slideNumber=' + slideNumber,

				success : function(data) {

				}

			});
			if (slideNumber == (horzSlideCnt - 1)) {
				$.ajax({

					type : 'POST',
					url : 'completeStatus.htm?topicId=5',

					success : function(data) {

					}
				});
			}/* end of IF */	 
			
		});
	</script>
</body>
</html>