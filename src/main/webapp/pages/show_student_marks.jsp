<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Knowledge Check Test</title>
<link href="css/site.css" rel="stylesheet" />
</head>

<body>
	<div>
		<center>
			<br /> <br />
			<h2>Student Name: ${firstName} ${lastName }</h2>

			<br /> <br />
			<c:if test="${status==true }">
				<div id="timer">
					<form class="form_login" action="/startExam" method="post">
						<center>
							<h3 style="color: #004D40;">Available Test: ${title }</h3>
							<input type="submit" class="timer" value="Start My Test" />
						</center>
					</form>

				</div>
			</c:if>

			<br />
			<br />
			<table width="80%">
				<tr>
					<th><h3>Knowledge Check</h3></th>
					<th><h3>Obtained Marks</h3></th>
				</tr>
				<c:forEach items="${scorelist}" var="score">
					<tr>

						<td>
							<center>
								<h4>${score.exam.title}</h4>
							</center>
						</td>
						<td><center>
								<h4>${score.value}</h4>
							</center></td>
					</tr>
				</c:forEach>
			</table>
		</center>
	</div>
	<br />
	<br />
	<div>
		<form method="get" action="/homepage">
			<td><input type="submit" value=">> Home Page"></input></td>
		</form>
	</div>

</body>
</html>