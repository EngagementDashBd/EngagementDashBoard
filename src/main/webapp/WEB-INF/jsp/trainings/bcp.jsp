<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/reveal.css" media="all">
<link rel="stylesheet" href="css/theme/white.css" id="theme">
<script src="script/easytimer.js" type="text/javascript"></script>
<script src="script/easytimer.min.js" type="text/javascript"></script>
<script src="script/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="script/jquery-ui-1.10.2.custom.min.js"
	type="text/javascript"></script>
<script src="script/head.min.js"></script>
<script src="script/reveal.js"></script>
<script src="script/custom.js" type="text/javascript"></script>
<script src="script/jquery.filer.min.js" type="text/javascript"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script> -->
<meta charset="ISO-8859-1">
<title>Business Continuity Plan</title>
<script>
	encodeURI();
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
									url : 'getSlide.htm?topicId=1',
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
		</div>  -->
		<div class="slides">
			<section id="slide1"> <img src="images\bcp\slide.PNG">
			</section>
			<section id="slide2"> <img src="images\bcp\slide1.PNG">
			</section>
			<section id="slide3"> <img src="images\bcp\slide2.PNG">
			</section>
			<section id="slide4"> <img src="images\bcp\slide3.PNG">
			</section>
			<section id="slide5"> <img src="images\bcp\slide4.PNG">
			</section>
			<section id="slide6"> <img src="images\bcp\slide5.PNG">
			</section>
			<section id="slide7"> <img src="images\bcp\slide6.PNG">
			</section>
			<section id="slide8"> <img src="images\bcp\slide7.PNG">
			</section>
			<section id="slide9"> <img src="images\bcp\slide8.PNG">
			</section>
			<section id="slide10"> <img src="images\bcp\slide9.PNG">
			</section>
			<section id="slide11">
			<h3 align="center">Thank you, Course Completed</h3>
			<p align="center">You can close the window now.</p>
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
			Reveal.configure({
				controls : false,
				keyboard : false,
			});
			timeExtension();

			slideNumber = (slidechanged.indexh);
			$.ajax({

				type : 'POST',
				url : 'updateSlide.htm?topicId=1&slideNumber=' + slideNumber,

				success : function(data) {

				}

			});
			if (slideNumber == (horzSlideCnt - 1)) {
				$.ajax({

					type : 'POST',
					url : 'completeStatus.htm?topicId=1',

					success : function(data) {

					}
				});
			}/* end of IF */
		});
	</script>
</body>
</html>