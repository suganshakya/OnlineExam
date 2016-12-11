<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Knowledge CheckList</title>
<link href="css/site.css" rel="stylesheet" />

<script type="text/javascript">
		function setId(id) {
			var a = document.getElementById("btnHidden");
			a.value = id;
		}
	</script>
</head>
<body>
	<center>
		<form method="post" action="/delete">
			<table width="90%">
				<tr>
					<th><h3>
							<b>List Of Test</b>
						</h3></th>
					<th><h3>
							<b>Question</b>
						</h3></th>
					<th><h3>
							<b>Type</b>
						</h3></th>
					<th><h3>
							<b>Correct Options</b>
						</h3></th>
					<th colspan="2"><h3>
							<b>Actions</b>
						</h3></th>

				</tr>
				<input type="hidden" name="btnHidden" id="btnHidden"></input>
				<%
					int l = 1;
				%>
				<c:forEach items="${question }" var="quest">
					<tr>
						<td><h4><%=l%></h4></td>
						<td><h4>

								<b>${quest.text }</b>
							</h4></td>
						<td><h4>${quest.type }</h4></td>
						<td><h4>${quest.correctAnswer }</h4></td>
						<input type=hidden name="myValue" value="<%=l%>" />
						<td colspan="2"><input type="submit" value="Delete"
							id="<%=l%>" name="Delete<%=l%>" onclick="setId(${quest.id})" /></td>
					</tr>
					<%
						l++;
					%>
				</c:forEach>
			</table>
		</form>

		<BR /> <BR />
		<form method="post" action="/redirectQuest">
			<td colspan="3"><input type="submit" value="Add New Questions" /></td>

		</form>
		<BR /> <BR />
	</center>
	<div>
		<form method="post" action="/redirect">
			<input type="hidden" name="name" value="${teacherName }"></input> <input
				type="hidden" name="password" value="${teacherPassword}"></input>
			<td><input type="submit" value=">> Main Page"></input></td>
		</form>
	</div>



</body>
</html>
