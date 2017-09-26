<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/reveal.css" media="all">
<link rel="stylesheet" href="css/theme/sky.css" id="theme">
<link rel="stylesheet" href="css/zenburn.css" id="theme">
<script src="script/easytimer.js" type="text/javascript"></script>
<script src="script/easytimer.min.js" type="text/javascript"></script>
<script src="script/head.min.js"></script>
<script src="script/reveal.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<script type="text/javascript"
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="script/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="script/jquery-ui-1.10.2.custom.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<title>Tool Assets</title>
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
									url : 'getSlide.htm?topicId=15',
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
			<div style="font-size: 20px" align="left" class="values"></div>
		</div> -->
		<div class="slides" align="center">
			<section id="slide1">
				<img src="images\continous\slide37.PNG">
			</section>
			<section id="slide2">
				
				<table style="font-size: 20px">
					<tr>
						<th>Sl.No</th>
						<th>Link</th>
						<th>Description</th>

					</tr>
					<tr>
						<th>1</th>
						<th><a href="https://newit.accenture.com/" target="_blank">New IT </a></th>
						<th>Train yourself in every aspect of NEW IT and become a
							powerhouse in the IT world. Get the tools you need from
							Accenture.</th>

					</tr>
					<tr>
						<th>2</th>
						<th><a
							href="https://sites.accenture.com/publishing/companymemos2016/home/february/accenture%20technology/pages/hostcentric--modernization-connection---q2-fy16.aspx" target="_blank">Host-centric
								Platform Professional Community </a></th>
						<th>Host-centric Platform Professional Community</th>
					</tr>
					<tr>
						<th>3</th>
						<th><a
							href="https://ts.accenture.com/sites/SIP-MainframeTechnicalLibrary/default.aspx?AssetID=290" target="_blank">Technical
								Library</a></th>
						<th>PL/I tools in Host-centric Technical Library</th>

					</tr>
					<tr>
						<th>4</th>
						<th><a href="https://artl.accenture.com/" target="_blank">Reusable
								Technical library for Mainframe </a></th>
						<th>Reusable Technical library for Mainframe</th>

					</tr>
					<tr>
						<th>5</th>
						<th><a
							href="https://ts.accenture.com/sites/SIP-MainframeTechnicalLibrary/default.aspx" target="_blank">
								Automation Tools and Industrialization Knowledge for Mainframe</a></th>
						<th>Automation Tools and Industrialization Knowledge for
							Mainframe</th>
					</tr>
				</table>
			</section>
			<section id="slide3">
				
				<table style="font-size: 20px">
					<tr>
						<th>Sl.No</th>
						<th>Link</th>
						<th>Description</th>
					</tr>
					<tr>
						<th>6</th>
						<th><a
							href="https://ajd.accenture.com/javaportal/#DeveloperCenter_tabs-121" target="_blank">Common
								Developer Help</a></th>
						<th>Common Developer Help</th>

					</tr>
					<tr>
						<th>7</th>
						<th><a
							href="https://ajd.accenture.com/javaportal/#d1_s_GoogleCodeProAnalytiX" target="_blank">Google
								Code Pro</a></th>
						<th>Google Code Pro</th>

					</tr>
					<tr>
						<th>8</th>
						<th><a
							href="https://ajd.accenture.com/javaportal/#d1_s_eclipseWindowBuilder" target="_blank">Eclipse
								WindowBuilder</a></th>
						<th>Eclipse Window Builder</th>
					</tr>
					<tr>
						<th>9</th>
						<th><a
							href="https://ajd.accenture.com/javaportal/#d1_s_JDepend" target="_blank">JDepend</a>
						</th>
						<th>JDepend</th>

					</tr>
					<tr>
						<th>10</th>
						<th><a
							href="https://ajd.accenture.com/javaportal/#d1_s_JRebel" target="_blank">JRebel</a>
						</th>
						<th>JRebel</th>
					</tr>

				</table>
			</section>
			<section id="slide4">
				<h3 align="center">Thank you, Course Completed</h3>
				<p align="center"><a href="navritiMenu.htm">Click Here</a> to redirect to Navriti Menu page.</p>
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
					url : 'updateSlide.htm?topicId=15&slideNumber=' + slideNumber,

					success : function(data) {

					}

				});
				if (slideNumber == (horzSlideCnt - 1)) {
					$.ajax({

						type : 'POST',
						url : 'completeStatus.htm?topicId=15',

						success : function(data) {

						}
					});
				}/* end of IF */	
			
		});
	</script>

</body>
</html>