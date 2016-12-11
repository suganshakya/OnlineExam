<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Popup contact form</title>
<link href="css/elements.css" rel="stylesheet">
<script src="js/my_js.js"></script>
</head>
<!-- Body Starts Here -->
<body id="body" style="overflow: hidden;">
	<div id="abc">
		<!-- Popup Div Starts Here -->
		<div id="popupContact">
			<!-- Contact Us Form -->
			<form action="#" id="form" method="post" name="form">
				<img id="close" src="images/3.png" onclick="div_hide()">
				<h2>Contact Us</h2>
				<hr>
				<input id="name" name="name" placeholder="Name" type="text">
				<input id="email" name="email" placeholder="Email" type="text">
				<textarea id="msg" name="message" placeholder="Message"></textarea>
				<a href="javascript:%20check_empty()" id="submit">Send</a>
			</form>
		</div>
		<!-- Popup Div Ends Here -->
	</div>
	<!-- Display Popup Button -->
	<h1>Click Button To Popup Form Using Javascript</h1>
	<button id="popup" onclick="div_show()">Popup</button>
</body>
<!-- Body Ends Here -->
</html>