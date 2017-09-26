<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Technology Stack</title>
<link rel="stylesheet" href="css/reveal.css" media="all">
<link rel="stylesheet" href="css/theme/simple.css" id="theme">
<script src="script/easytimer.js" type="text/javascript"></script>
<script src="script/head.min.js"></script>
<script src="script/reveal.js"></script>
<script src="script/easytimer.min.js" type="text/javascript"></script>
<script src="script/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="script/jquery-ui-1.10.2.custom.min.js"
	type="text/javascript"></script>

<script>
	function timer() {
		var timer = new Timer();
		timer.start({
			countdown : true,
			startValues : {
				seconds : 20
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
									url : 'getSlide.htm?topicId=7',
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
</head>
<body>
	<div class="reveal">
		<!-- <div id="countdownExample">
			<div style="font-size:20px" align="left" class="values"></div>
		</div> -->
		<div class="slides" align="center">
			<section id="slide1">
				<img src="images/tech/Slide1.PNG">
			</section>
			<section id="slide2">
				<img src="images/tech/Slide2.PNG">
			</section>
			<section id="slide3">
				<img src="images/tech/Slide3.PNG">
			</section>
			<section id="slide4">
				<img src="images/tech/Slide4.PNG">
			</section>
			<section id="slide5">
				<table style="font-size: 20%; border: 2">
					<div class="Ebook">
						<p class="tech">
							Mainframe Learning Boards<br> 
								<a class="data"
								href="https://connectedlearning.accenture.com/learningboard/learn-about-ibm-mainframes" target="_blank">Learn
								about IBM mainframes</a> <br> 
								<a class="data"
								href="https://connectedlearning.accenture.com/learningboard/all-about-system-z-and-zos" target="_blank">All
								About System z and Z/OS</a> <br> 
								<a class="data"
								href="https://connectedlearning.accenture.com/learningboard/mainframe-modernization" target="_blank">Mainframe
								Modernization</a> <br> 
								<a class="data"
								href="https://connectedlearning.accenture.com/learningboard/welcome-to-system-i-world" target="_blank">Welcome
								to System i World</a>
						</p>

						<p class="tech">
							Host centric KX links<br> <a class="data"
								href="https://kxsites.accenture.com/groups/hostcentric/default.aspx" target="_blank">Host
								centric Platform Professional Community</a> <br>
								<a class="data"
								href="https://ts.accenture.com/sites/SIP-MainframeTechnicalLibrary/default.aspx" target="_blank">Host
								centric Platform Technical Library</a> <br>
								<a class="data"
								href="https://kxsites.accenture.com/groups/devops/Pages/TopicPages/Mainframe.aspx" target="_blank">Mainframe
								DevOps community</a>
						</p>
					</div>
				</table>
			</section>
			<section id="slide6">
				<!-- <img src="images/tech/Slide6.PNG"> -->
				<div class="Ebook">
					
						<h4>E-books</h4>
					
					
						<a class="data"
						href="   http://mmlviewer.books24x7.com/toc.asp?bookid=24554" target="_blank">Introduction
						to the New Mainframe: z/OS Basics</a>
						<br>
					
						<a class="data"
						href="http://mmlviewer.books24x7.com/toc.asp?bookid=224" target="_blank">Mastering
						Cobol</a>
						<br>
					
						<a class="data"
						href=" http://mmlviewer.books24x7.com/toc.asp?bookid=11225" target="_blank">REXX
						Programmer's Reference</a>
						<br>
					 
						<a class="data"
						href="  http://mmlviewer.books24x7.com/toc.asp?bookid=498" target="_blank">Mainframe
						Assembler Programming</a>
					
					</div>
				</section>

		<section id="slide7">
			<!-- <img src="images/tech/Slide6.PNG"> -->
			<div class="Ebook">
				<p class="tech">Healthcare Trainings</p>
				<p class="tech">
					<font size="5">REL43273 Health Fundamentals</font>
				</p>
				<p class="tech">
					<font size="5">Z11584 Healthcare Claims: An Introduction</font>
				</p>
				<p class="tech">
					<font size="5">REL87086 HC201 - Claims Processing</font>
				</p>
				<p class="tech">
					<font size="5">REL61959 HC201 - Provider Data Management</font>
				</p>
				<p class="tech">
					<font size="5">Z95022 ICD Overview</font>
				</p>

				<p class="tech">ATA(Accenture Technology Academy)certification</p>

				<p class="tech">
					<font size="25"> 
					<a class="data"
						href=" https://mycertification.accenture.com/web/Dashboard?certificationId=974"
						target="_blank">Mainframe Application Designer</a> 
					<a class="data"
						href="https://mycertification.accenture.com/web/Dashboard?certificationId=976"
						target="_blank">Mainframe Application Developer</a><br>
					<a class="data"
						href=" https://mycertification.accenture.com/web/Dashboard?certificationId=627"
						target="_blank">Agile Professional Certification</a> 
					<a
						class="data"
						href="https://mycertification.accenture.com/web/Dashboard?certificationId=216"
						target="_blank">US Health Industry Fundamentals Certification</a>
					</font>
				</p>

			</div>
		</section>
		<section id="slide8">
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
				url : 'updateSlide.htm?topicId=7&slideNumber=' + slideNumber,

				success : function(data) {

				}

			});
			if (slideNumber == (horzSlideCnt - 1)) {
				$.ajax({

					type : 'POST',
					url : 'completeStatus.htm?topicId=7',

					success : function(data) {

					}
				});
			}/* end of IF */	 
		}); 
	</script>
</body>
</html>