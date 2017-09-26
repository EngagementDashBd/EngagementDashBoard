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
<script type="text/javascript"
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="script/head.min.js"></script>
	<script src="script/reveal.js"></script>

<title>Health Overview</title>
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
									url : 'getSlide.htm?topicId=4',
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
				<img src="images\health\Slide1.PNG"> 
			</section>
			<section id="slide2">
				<img src="images\health\Slide2.PNG">
			</section>
			<section id="slide3">
				<img src="images\health\Slide3.PNG">
			</section>
			<section id="slide4">
				<img src="images\health\Slide4.PNG">
			</section>
			<section id="slide5">
				<img src="images\health\Slide5.PNG">
			</section>
			<section id="slide6">
				<img src="images\health\Slide6.PNG">
			</section>
			<section id="slide7">
				<img src="images\health\Slide7.PNG">
			</section>
			<section id="slide8">
				<img src="images\health\Slide8.PNG">
			</section>
			<section id="slide9">
				<img src="images\health\Slide9.PNG">
			</section>
			<section id="slide10">
				<img src="images\health\Slide10.PNG">
			</section>
			<section id="slide11">
				<img src="images\health\Slide11.PNG">
			</section>
			<section id="slide12">
				<img src="images\health\Slide12.PNG">
			</section>
			<section id="slide13">
				<img src="images\health\Slide13.PNG">
			</section>
			<section id="slide14">
				<img src="images\health\Slide14.PNG">
			</section>
			<section id="slide15">
				<img src="images\health\Slide15.PNG">
			</section>
			<section id="slide16">
				<img src="images\health\Slide16.PNG">
			</section>
			<section id="slide17">
				<img src="images\health\Slide17.PNG">
			</section>
			<section id="slide18">
				<img src="images\health\Slide18.PNG">
			</section>
			<section id="slide19">
				<img src="images\health\Slide19.PNG">
			</section>
			<section id="slide20">
				<img src="images\health\Slide20.PNG">
			</section>
			<section id="slide21">
				<img src="images\health\Slide21.PNG">
			</section>
			<section id="slide22">
				<img src="images\health\Slide22.PNG">
			</section>
			<section id="slide23">
				<img src="images\health\Slide23.PNG">
			</section>
			<section id="slide24">
				<img src="images\health\Slide24.PNG">
			</section>
			<section id="slide25">
				<img src="images\health\Slide25.PNG">
			</section>
			<section id="slide26">
				<img src="images\health\Slide26.PNG">
			</section>
			<section id="slide27">
				<img src="images\health\Slide27.PNG">
			</section>
			<section id="slide28">
				<img src="images\health\Slide28.PNG">
			</section>
			<section id="slide29">
				<img src="images\health\Slide29.PNG">
			</section>
			<section id="slide30">
				<img src="images\health\Slide30.PNG">
			</section>
			<section id="slide31">
				<img src="images\health\Slide31.PNG">
			</section>
			<section id="slide32">
				<img src="images\health\Slide32.PNG">
			</section>
			<section id="slide33">
				<img src="images\health\Slide33.PNG">
			</section>
			<section id="slide34">
				<img src="images\health\Slide34.PNG">
			</section>
			<section id="slide35">
				<img src="images\health\Slide35.PNG">
			</section>
			<section id="slide36">
				<img src="images\health\Slide36.PNG">
			</section>
			<section id="slide37">
				<img src="images\health\Slide37.PNG">
			</section>
			<section id="slide38">
				<img src="images\health\Slide38.PNG">
			</section>
			<section id="slide39">
				<img src="images\health\Slide39.PNG">
			</section>
			<section id="slide40">
				<img src="images\health\Slide40.PNG">
			</section>
			<section id="slide41">
				<img src="images\health\Slide41.PNG">
			</section>
			<section id="slide42">
				<img src="images\health\Slide42.PNG">
			</section>
			<section id="slide43">
				<img src="images\health\Slide43.PNG">
			</section>
			<section id="slide44">
				<img src="images\health\Slide44.PNG">
			</section>
			<section id="slide45">
				<img src="images\health\Slide45.PNG">
			</section>
			<section id="slide46">
				<img src="images\health\Slide46.PNG">
			</section>
			<section id="slide47">
				<img src="images\health\Slide47.PNG">
			</section>
			<section id="slide48">
				<img src="images\health\Slide48.PNG">
			</section>
			<section id="slide49">
				<img src="images\health\Slide49.PNG">
			</section>
			<section id="slide50">
				<img src="images\health\Slide50.PNG">
			</section>
			<section id="slide51">
				<img src="images\health\Slide51.PNG">
			</section>
			<section id="slide52">
				<img src="images\health\Slide52.PNG">
			</section>
			<section id="slide53">
				<img src="images\health\Slide53.PNG">
			</section>
			<section id="slide54">
				<img src="images\health\Slide54.PNG">
			</section>
			<section id="slide55">
				<img src="images\health\Slide55.PNG">
			</section>
			<section id="slide56">
				<img src="images\health\Slide56.PNG">
			</section>
			<section id="slide57">
				<img src="images\health\Slide57.PNG">
			</section>
			<section id="slide58">
				<img src="images\health\Slide58.PNG">
			</section>
			<section id="slide59">
				<img src="images\health\Slide59.PNG">
			</section>
			<section id="slide60">
				<img src="images\health\Slide60.PNG">
			</section>
			<section id="slide61">
				<img src="images\health\Slide61.PNG">
			</section>
			<section id="slide62">
				<img src="images\health\Slide62.PNG">
			</section>
			<section id="slide63">
				<img src="images\health\Slide63.PNG">
			</section>
			<section id="slide64">
				<img src="images\health\Slide64.PNG">
			</section>
			<section id="slide65">
				<img src="images\health\Slide65.PNG">
			</section>
			<section id="slide66">
				<img src="images\health\Slide66.PNG">
			</section>
			<section id="slide67">
				<img src="images\health\Slide67.PNG">
			</section>
			<section id="slide68">
				<img src="images\health\Slide68.PNG">
			</section>
			<section id="slide69">
				<img src="images\health\Slide69.PNG">
			</section>
			<section id="slide70">
				<img src="images\health\Slide70.PNG">
			</section>
			<section id="slide71">
				<h3>Accenture Training courses</h3>
				<table border="1" style="font-size: 70%">
					<tr>
						<th>Course Name</th>
						<th>Course Code/Link in MyLearning</th>
					</tr>
					<tr>
						<td>Accenture Health Industry Overview</td>
						<td><a
							href="https://mylearning.accenture.com/myl-ui/learner/activityDetails?referrer=search&activityID=769974&source=myLearning&refresh=1841.1621477741305"
							target="_blank">Z22923</a></td>
					</tr>
					<tr>
						<td>US HealthCare Ecosystem Overview</td>
						<td><a
							href="https://mylearning.accenture.com/myl-ui/learner/activityDetails?referrer=search&activityID=1265161&source=myLearning&refresh=1129.9815862470275"
							target="_blank">Z58043</a></td>
					</tr>
					<tr>
						<td>Public Health Overview Course</td>
						<td><a
							href="https://mylearning.accenture.com/myl-ui/learner/activityDetails?referrer=search&activityID=1139986&source=myLearning&refresh=1790.0910733970447"
							target="_blank">Z82679</a></td>
					</tr>
					<tr>
						<td>Payer Overview</td>
						<td><a
							href="https://mylearning.accenture.com/myl-ui/learner/activityDetails?referrer=search&activityID=1186565&source=myLearning&refresh=1207.4395115222196"
							target="_blank">Z66002</a></td>
					</tr>
					<tr>
						<td>HealthCare Provider Overview</td>
						<td><a
							href="https://mylearning.accenture.com/myl-ui/learner/activityDetails?referrer=search&activityID=1390006&source=myLearning&refresh=1578.3496650835163"
							target="_blank">Z95461</a></td>
					</tr>
					<tr>
						<td>Federal Health Overview</td>
						<td><a
							href="https://mylearning.accenture.com/myl-ui/learner/activityDetails?referrer=search&activityID=1356574&source=myLearning&refresh=1036.2722415614507"
							target="_blank">Z76877</a></td>
					</tr>
					<tr>
						<td>Health Fundamentals</td>
						<td><a
							href="https://mylearning.accenture.com/myl-ui/learner/activityDetails?referrer=search&activityID=1162965&source=myLearning&refresh=1708.0929445051492"
							target="_blank">REL43273</a></td>
					</tr>
				</table>
			</section>
			<section id="slide72">
				<h3 align="center">Thank you, Course Completed</h3>
				<p align="center"><a href="healthOverviewMenu.htm">Click Here</a> to redirect to HealthCare Menu page.</p>
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
		
		/* timer(); */
		
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
				url : 'updateSlide.htm?topicId=4&slideNumber=' + slideNumber,

				success : function(data) {

				}

			});
			if (slideNumber == (horzSlideCnt - 1)) {
				$.ajax({

					type : 'POST',
					url : 'completeStatus.htm?topicId=4',

					success : function(data) {

					}
				});
			}/* end of IF */	 
		}); 
	</script>
</body>
</html>