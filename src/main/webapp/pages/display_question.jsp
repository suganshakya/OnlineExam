<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Knowledge Check Test</title>
<link href="css/displayqn.css" rel="stylesheet" />
</head>

<!-- Timer -->
<script language="JavaScript" type="text/javascript">

	var sec = 00; // set the seconds
	var min = 20; // set the minutes

	function countDown() {
		sec--;
		if (sec == -01) {
			sec = 59;
			min = min - 1;
		} else {
			min = min;
		}

		if (sec <= 9) {
			sec = "0" + sec;
		}

		time = (min <= 9 ? "0" + min : min) + " min and " + sec + " sec ";

		if (document.getElementById) {
			document.getElementById('theTime').innerHTML = time;
		}

		SD = window.setTimeout("countDown();", 1000);
		if (min == '00' && sec == '00') {
			sec = "00";
			window.clearTimeout(SD);
			var QnCount =document.getElementById("totalQuestion").innerHTML; 
			var form = document.getElementById("submitResult");
			alert("Time Up!, please press OK to continue.: "+QnCount);
			calcAnswer(QnCount,form);
		}
	}
	window.onload = countDown;

	function showDivCheck(id) {
		document.getElementById('option_div' + id).style.display = "block";
		alert(id);
	}

	function getRadioValue(form, name,qId) {
			var radios = form.elements[name];
			var val;

			for (var i = 0, len = radios.length; i < len; i++) {
				if (radios[i].checked == true) {
					val = radios[i].value;
					break;
				}
			}
			var fieldforText = form.elements["ansField"+qId];
			var selectedOption = val.split("-");
			var opt = selectedOption[selectedOption.length-1];
			
			var opnName = ["A", "B","C","D","E","F","G","H","I","J"];
			fieldforText.value = opnName[opt];
	}
	
	function getCheckedValue(form, name, qId) {
			var checkbox = form.elements[name];
			var val;
			var fieldforText = form.elements["ansField"+qId];
			var option=fieldforText.value ;
			var opnName = ["A", "B","C","D","E","F","G","H","I","J"];
			
			val = checkbox.value;
			
			var selectedOption = val.split("-");
			var opt = selectedOption[selectedOption.length-1];
			
					if(checkbox.checked == true){
						option = option+opnName[opt];
					}else{
						option = option.replace(opnName[opt],"");
					}
			
					var aa = option.split("");
					aa.sort();
					option = aa.join("");
					
			fieldforText.value = option;
			
	}
	
	function getTextFieldValue(form, name, qId){
		var text = form.getElementsByTagName("input");
		var i=0;
		var option="";
		var fieldforText = form.elements["ansField"+qId];
		while(i<text.length-1){
			if(text[i].type.toLowerCase()=="text"){
				var t = text[i].value;
				option+=t;

			}
				
			i++;
		}
		fieldforText.value = option.toUpperCase();
	}
	
	function calcAnswer(totalQn,form){
		var ans = document.getElementsByTagName("input");
		var ansCollection="";
		var i=0,j=0;
		while(i<ans.length){
			if(ans[i].type.toLowerCase()=="hidden"){
				if(ans[i].id!= "studentAnswer"){
				var temp = ans[i].value;
				if(temp!=""){
				ansCollection +=temp+"____";
				j++;
				}else{
					ansCollection+="null____";
				}
				}
			}
			i++;
		}
		var finalAnswer = document.getElementById("studentAnswer");
		finalAnswer.value = ansCollection;
		form.submit();
	}
	
</script>

<body>
	<div id="timer">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center"><h3>Student Name: ${firstName}
						${lastName}</h3></td>
				<td align="right"><img src="/images/stopwatch.png"
					style="width: 50px; height: 50px;" /></td>
				<td><h3>
						Time Remaining: <label id="theTime"></label>
					</h3></td>
			</tr>
			<br />
			<tr>
				<td><center>
						<h4>
							Total Question: <label id="totalQuestion">${fn:length(question)}</label>
						</h4>
					</center></td>
			</tr>
		</table>
	</div>
	<!-- Timer End -->



	<div>
		<form name="question">
			<ol type="1">
				<c:set var="option" value="${options}" />
				<c:set var="qt" value="${opType}" />
				<c:forEach items="${question}" var="quest" varStatus="j">
					<br />
					<form name="${j.index }" id="${j.index }">
						<div>
							<li>
								<p>
									<b>${quest }</b>
								</p>
							</li>
							<ol type="A">
								<c:forEach items="${option[j.index]}" var="opn" varStatus="i">
									<li><p>
											<c:if test="${qt[j.index]=='Multiple Choice'}">
												<input type="checkbox" name="${j.index }-${i.index}"
													id="${j.index }-${i.index}"
													onclick="getCheckedValue(this.form, this.id,${j.index});"
													value="${j.index}-${i.index}" />${opn }
									</c:if>
											<c:if test="${qt[j.index]== 'Single Choice'}">
												<input type="radio" value="${j.index }-${i.index}"
													name="radio${j.index  }"
													onclick="getRadioValue(this.form, this.id,${j.index});"
													id="radio${j.index}" />${opn }
									</c:if>
											<c:if test="${qt[j.index]== 'True/False'}">
												<input type="radio" value="${j.index }-${i.index}"
													name="radio${j.index } "
													onclick="getRadioValue(this.form, this.id,${j.index});"
													id="radio${j.index }" />${opn }
									</c:if>
											<c:if test="${qt[j.index]== 'Correct Order'}">
										${opn}  <input type="text" name="${j.index }-${i.index}"
													id="${j.index }-${i.index}"
													onkeyup="getTextFieldValue(this.form, this.id,${j.index});"
													onkeypress="getTextFieldValue(this.form, this.id,${j.index});"
													size="4px" />
											</c:if>
										</p></li>
								</c:forEach>
							</ol>
							<br /> <input type="hidden" readonly="readonly" value=""
								name="ansField${j.index }" id="ansField${j.index}"
								style="position: absolute; right: 250px;" size="5px" /> <br />
						</div>
					</form>
					<br />
				</c:forEach>
			</ol>


		</form>
	</div>
	<form name="submitResult" id="submitResult" method="post"
		action="/submitTestResult">
		<input type="hidden" value="" name="studentAnswer" id="studentAnswer"
			readonly="readonly" />
		<div align="center">
			<br /> <br /> <input type="submit"
				onclick="calcAnswer(${fn:length(question)});" value="Submit" /> <br />
			<br />
		</div>
	</form>
</body>

</html>