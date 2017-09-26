<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script src="script/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="script/jquery-ui-1.10.2.custom.min.js"
	type="text/javascript"></script>
<meta charset="ISO-8859-1">
<title>Health Care Day 2</title>
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
		timer.addEventListener('targetAchieved', function(e) {
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
		timer1.addEventListener('targetAchieved', function(e) {
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
									url : 'getSlide.htm?topicId=9',
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
		<div id="main">
			<h6 align="center">HEALTH CARE OVERVIEW DAY 2</h6>

			<div class="slides">
				<section id="slide1">
					<img src="images\health\day2\Slide1.PNG">
				</section>
				<section id="slide2">
					<img src="images\health\day2\Slide2.PNG">
				</section>
				<section id="slide3">
					<img src="images\health\day2\Slide3.PNG">
				</section>

				<section id="slide4">
					<img src="images\health\day2\Slide4.PNG">
				</section>

				<section id="slide5">
					<img src="images\health\day2\Slide5.PNG">
				</section>

				<section id="slide6">
					<img src="images\health\day2\Slide6.PNG">
				</section>

				<section id="slide7">
					<img src="images\health\day2\Slide7.PNG">
				</section>

				<section id="slide8">
					<img src="images\health\day2\Slide8.PNG">
				</section>
				<section id="slide9">
					<img src="images\health\day2\Slide9.PNG">
				</section>
				<section id="slide10">
					<img src="images\health\day2\Slide10.PNG">
				</section>
				<section id="slide11">
					<img src="images\health\day2\Slide11.PNG">
				</section>

				<section id="slide12">
					<img src="images\health\day2\Slide12.PNG">
				</section>

				<section id="slide13">
					<img src="images\health\day2\Slide13.PNG">
				</section>

				<section id="slide14">
					<img src="images\health\day2\Slide14.PNG">
				</section>
				<section id="slide15">
				<h3 align="center">Thank you, Course Completed</h3>
				<p align="center"><a href="healthOverviewMenu.htm">Click Here</a> to redirect to HealthCare Menu page.</p>
			</section>
			</div>
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
				url : 'updateSlide.htm?topicId=9&slideNumber=' + slideNumber,

				success : function(data) {

				}

			});
			if (slideNumber == (horzSlideCnt - 1)) {
				$.ajax({

					type : 'POST',
					url : 'completeStatus.htm?topicId=9',

					success : function(data) {

					}
				});
			}/* end of IF */	
		});
	</script>
</body>
</html>