<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Knowledge Check Test</title>
<link href="css/site.css" rel="stylesheet" />
<SCRIPT type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</SCRIPT>
</head>

<!-- Timer -->
<body>
	<div">
		<form name="question">
			<ol type="1">
				Subject:
				<td>${Title}</td>
				<br />
				<c:forEach items="${score_list}" var="score">
					<br />
					<tr>
						<td>${score.student}</td>
						<tab />
						Marks:
						<td>${score.value}</td>
					</tr>
					<br />
				</c:forEach>
			</ol>
			<input type="button" value="OK" onclick=";" />
		</form>
	</div>
	<div>
		<form method="post" action="/redirect">
			<td><input type="submit" value=">> Main Page"></input></td>
		</form>
	</div>
</body>
</html>