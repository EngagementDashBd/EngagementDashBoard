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
<script src="script/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="script/jquery-ui-1.10.2.custom.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<script src="script/head.min.js"></script>
<script src="script/reveal.js"></script>
<script type="text/javascript"
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<title>Innovation at a Glance</title>
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
									url : 'getSlide.htm?topicId=2',
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
			<section id="slide1"> <img
				src="images\continous\Slide1.PNG"> </section>
			<section id="slide2"> <img
				src="images\continous\Slide2.PNG"> </section>
			<section id="slide3"> <img
				src="images\continous\Slide3.PNG"> </section>
			<section id="slide4"> <img
				src="images\continous\Slide4.PNG"> </section>
			<section id="slide5"> <img
				src="images\continous\Slide5.PNG"> </section>
			<section id="slide6"> <img
				src="images\continous\Slide6.PNG"> </section>
			<section id="slide7"> <img
				src="images\continous\Slide7.PNG"> </section>
			<section id="slide8"> <img
				src="images\continous\Slide8.PNG"> </section>
			<section id="slide9"> <img
				src="images\continous\Slide9.PNG"> </section>
			<section id="slide10"> <img
				src="images\continous\Slide10.PNG"> </section>
			<section id="slide11"> <img
				src="images\continous\Slide11.PNG"> </section>
			<section id="slide12"> <img
				src="images\continous\Slide12.PNG"> </section>
			<section id="slide13"> <img
				src="images\continous\Slide13.PNG"> </section>
			<section id="slide14"> <img
				src="images\continous\Slide14.PNG"> </section>
			<section id="slide15"> <img
				src="images\continous\Slide15.PNG"> </section>
			<section id="slide16"> <img
				src="images\continous\Slide16.PNG"> </section>
			<section id="slide17"> <img
				src="images\continous\Slide17.PNG"> </section>
			<section id="slide18"> <img
				src="images\continous\Slide18.PNG"> </section>
			<section id="slide19"> <img
				src="images\continous\Slide19.PNG"> </section>
			<section id="slide20"> <img
				src="images\continous\Slide20.PNG"> </section>
			<section id="slide21"> <img
				src="images\continous\Slide21.PNG"> </section>
			<section id="slide22"> <img
				src="images\continous\Slide22.PNG"> </section>
			<section id="slide23"> <img
				src="images\continous\Slide23.PNG"> </section>
			<section id="slide24"> <img
				src="images\continous\Slide24.PNG"> </section>
			<section id="slide25"> <img
				src="images\continous\Slide25.PNG"> </section>
			<section id="slide26"> <img
				src="images\continous\Slide26.PNG"> </section>
			<section id="slide27"> <img
				src="images\continous\Slide27.PNG"> </section>
			<section id="slide28"> <img
				src="images\continous\Slide28.PNG"> </section>
			<section id="slide29"> <img
				src="images\continous\Slide29.PNG"> </section>
			<section id="slide30"> <img
				src="images\continous\Slide30.PNG"> </section>
			<section id="slide31"> <img
				src="images\continous\Slide31.PNG"> </section>
			<section id="slide32"> <img
				src="images\continous\Slide32.PNG"> </section>
			<section id="slide33"> <img
				src="images\continous\Slide33.PNG"> </section>
			<section id="slide34"> <img
				src="images\continous\Slide34.PNG"> </section>
			<section id="slide35"> <img
				src="images\continous\Slide35.PNG"> </section>
			<section id="slide36">
			<h3 align="center">Thank you, Course Completed</h3>
			<p align="center">
				<a href="navritiMenu.htm">Click Here</a> to redirect to Navriti Menu
				page.
			</p>
			</section>
		</div>
	</div>

	<script>
		var horzSlideCnt = document.querySelectorAll('.reveal .slides>section').length;

		Reveal.initialize({
			center : true,
			controls : false,
			keyboard : false,
			progress : false,
			mouseWheel : false,
			transition : 'convex',
			width : 960,
			height : 750,
		});

		Reveal.addEventListener('slidechanged', function(slidechanged) {
			// event.previousSlide, event.currentSlide, event.indexh, event.indexv
			Reveal.configure({
				controls : false,
				keyboard : false,
			});

			slideNumber = (slidechanged.indexh);

			$.ajax({

				type : 'POST',
				url : 'updateSlide.htm?topicId=2&slideNumber=' + slideNumber,

				success : function(data) {

				}

			});
			if (slideNumber == (horzSlideCnt - 1)) {
				$.ajax({

					type : 'POST',
					url : 'completeStatus.htm?topicId=2',

					success : function(data) {

					}
				});
			}/* end of IF */
			if (slideNumber == 6 || slideNumber == 1) {
				timer();
			} else {
				timeExtension();
			}
			/* if(slideNumber == (horzSlideCnt - 1)){ 
				 setTimeout(function(){ alert("End of Presentation, Redirecting to Menu");
					window.location = "continuous.html";}, 5000);
			 }
			 */

		});
	</script>
</body>
</html>