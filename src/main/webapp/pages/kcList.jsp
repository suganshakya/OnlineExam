<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Knowledge Check Test List</title>
<link href="css/site.css" rel="stylesheet" />
<script type="text/javascript">
		function setId(id) {
			var a = document.getElementById("btnHidden");
			a.value = "Update"+"_"+id;

		}
		
		function setStatus(ch,form,id){
			var rr = document.getElementById("btnHidden");
			rr.value = "Check"+"_"+id;
			var checkstat=document.getElementById("checkStatus");
			
			
		
			if(ch.checked){
				checkstat.value="true";
			}else{
				checkstat.value="false";
			}
			form.submit();
			
			
			
		}
		
		function setDel(id){
			var dd=document.getElementById("btnHidden");
			dd.value="Delete"+"_"+id;
			
		}
		
		//for validation
		function emptyCheck(form){
			var textField = document.getElementById("Subject");
			if(textField.value==""){
				 document.getElementById('errmsg').innerHTML = 'Field is Empty!';
			}else{
				 document.getElementById('errmsg').innerHTML ='';
				form.submit();
			}
		}
	</script>
</head>
<body>
	<div>
		<form name="KnowledgeCheck" method="post" action="/redirectKC"
			modelAttribute="KCLister">
			<center>
				<table width="90%" style="border: 0px solid white;">
					<tr>
						<th><h3>S. No.</h3></th>
						<th><h3>List Of Test</h3></th>
						<th colspan=3><h3>Actions</h3></th>
						<th><h3>Publish Status</h3></th>
						<th><h3>View Results</h3></th>
					</tr>


					<%
						int i = 1;
					%>
					<c:forEach items="${examList}" var="exam">
						<tr>
							<td><h4><%=i%></h4></td>
							<td><h4>${exam.title }</h4></td>
							<td><h4>
									<input type="submit" value="Add/Update Questions"
										onclick="setId(${exam.id})" />
								</h4></td>
							<td><h4>
									<input type="submit" value="Delete"
										onclick="setDel(${exam.id})"></input>
								</h4></td>
							<c:choose>
								<c:when test="${exam.status }">
									<td><h4>
											<input type="checkbox" id="CheckBox" checked="true"
												onclick="setStatus(this,this.form,${exam.id});"></input>Publish
										</h4></td>
								</c:when>
								<c:otherwise>
									<td><h4>
											<input type="checkbox" id="CheckBox"
												onclick="return setStatus(this,this.form,${exam.id});"></input>Publish
										</h4></td>
								</c:otherwise>
							</c:choose>
							<td><h4>${exam.status }</h4></td>
							<td><a href="displayExamResults?getId=${exam.id }">View
									Students Result</a></td>
						</tr>
						<%
							i++;
						%>

					</c:forEach>
				</table>
			</center>
			<input type="hidden" id="btnHidden" name="btnHidden"></input> <input
				type="hidden" id="checkStatus" name="checkStatus"></input>
		</form>
	</div>
	<br />
	<br />
	<center>
		<div>
			<form method="post" action="/createExam">
				<td><input type="text" name="Subject" id="Subject"
					placeholder="Enter Title of Test" size="40px"></input></td>
				<td><input type="button" value="Add Knowledge Check"
					onclick="emptyCheck(this.form);" /></td> <label id="errmsg"
					style="color: white;"></label>
			</form>
			<br /> <br />
		</div>

	</center>
	<right>
	<a href="pages/teacher_register.jsp" target=""><h3>Register Admin</h3></a></right>
	</div>
	<div>
		<form method="get" action="/homepage">
			<input type="submit" value=">> Home Page (Log Out)"></input> <br></br>
			<br></br> <br></br>

		</form>
	</div>
	</div>


</body>
</html>
