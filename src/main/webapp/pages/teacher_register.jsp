<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body {
	font-family: "Trebuchet MS", Verdana, sans-serif;
	font-size: 14px;
	background-color: #0a9ec7;
	color: #696969;
	padding: 3px;
}

#header {
	width: 500px;
	padding: 5px;
	padding-left: 15px;
	padding-right: 15px;
	background-color: #1591b5;
	box-shadow: 5px 5px 2px #3c3c3c;
	border-radius: 0px 0px 20px 20px;
}

h1 {
	font-family: Georgia, serif;
	color: #ffffff;
	font-size: 30px;
}

h2 {
	font-family: Georgia, serif;
	color: #ffffff;
	font-size: 20px;
}

input[type=submit] {
	padding: 0 18px;
	height: 29px;
	font-size: 12px;
	font-weight: bold;
	color: #527881;
	text-shadow: 0 1px #e3f1f1;
	background: #cde5ef;
	border: 1px solid;
	border-color: #b4ccce #b3c0c8 #9eb9c2;
	border-radius: 16px;
	outline: 0; @ include box-sizing(content-box); // Firefox sets this to
	border-box by default @include linear-gradient(top, #edf5f8, #cde5ef);
	@ include box-shadow(inset 0 1px white, 0 1px 2px rgba(black, .15)); &:
	active { background : #cde5ef;
	border-color: #9eb9c2 #b3c0c8 #b4ccce;
	@
	include
	box-shadow(inset
	0
	0
	3px
	rgba(black,
	.2));
}
</style>
<title>Insert title here</title>
</head>
<body>
	<br />
	<br />
	<br />
	<center>
		<div id="header">
			<form action="/registerTeacher" method="post">
				<div class="login">
					<center>
						<h2>Teacher Registration</h2>
						<br />
						<p>
							<input type="text" name="userName" 
								placeholder="User Name">
						</p>
						<p>
							<input type="password" name="password" 
								placeholder="Password">
						</p>
						<p class="submit">
							<input type="submit" name="submitName" value="Register">
						</p>
						<br /> <label style="color: white;">${msg }</label>

					</center>
				</div>
			</form>
			<br />
			<h2>
				<label style="color: white;">${success }</label>
			</h2>
		</div>
		<br></br>
		<br></br>
		<br></br>
		<div>
			<form method="get" action="/homepage">
				<td><input type="submit" value=">> Home Page"></input></td>
			</form>
		</div>
	</center>
</body>
</html>