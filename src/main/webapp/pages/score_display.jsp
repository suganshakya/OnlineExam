<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Score Display</title>
<link href="css/site.css" rel="stylesheet" />
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<br />
	<br />
	<center>
		<div id="scorePane">

			<h2>Your score has been successfully submitted.</h2>
			<hr width="40%">
			<h2>Your Score</h2>
			<h1>${score }/${totalQn }</h1>
			<br /> <br /> <br />
			<h2>Wrong Answers:</h2>
			<hr width="40%">
			<c:forEach items="${wrongAnswer}" var="wrong">
					<h4>${wrong }</h4>

			</c:forEach>

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