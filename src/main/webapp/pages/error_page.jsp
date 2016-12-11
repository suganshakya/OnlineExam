<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Score Display</title>
<link href="css/site.css" rel="stylesheet" />
</head>
<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">
	<br />
	<br />
	<center>
		<div id="scorePane">
		<center>
			<h2>Submission Error:</h2>
			<h2>Your already submitted the result of this test.</h2>
			</center>

		</div>
		<br></br> <br></br> <br></br>
		<div>
			<form method="get" action="/homepage">
				<td><input type="submit" value=">> Home Page"></input></td>
			</form>
		</div>

	</center>

</body>
</html>