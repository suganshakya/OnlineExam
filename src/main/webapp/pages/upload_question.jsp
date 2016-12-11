<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Question</title>
<link href="css/displayqn.css" rel="stylesheet" />
<script type="text/javascript" src="//code.jquery.com/jquery-latest.js"></script>
<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>

<script>
	tinymce
			.init({
				selector : 'textarea',
				theme : 'modern',
				width : 800,
				height : 20,
				menubar : false,
				plugins : [
						'advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker',
						'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
						'save table contextmenu directionality emoticons template paste textcolor' ],
				content_css : 'css/content.css',
				toolbar : 'insertfile undo redo | styleselect | bold italic  | link image |  preview fullpage | forecolor emoticons',
				inline : false
			});
</script>


</head>
<body>

	<!-- Java Script Functions -->
	<script>
		function reloadPage() {
			location.reload();
		}

		function manageOption(form) {
			var hidField = document.getElementById("options");

			var i = 1;
			var opn = "";
			while (i < 11) {
				var text = document.getElementById("textbox" + i);
				if (text != null) {
					var valueofText = text.value;
					opn += valueofText + "____";
				}
				i++;
			}
			hidField.value = opn;

			form.submit();

		}
	</script>
	<!-- Java Script Functions -->
	<div id="timer">
		<center>
			<h2>Add Question</h2>
		</center>
	</div>
	<div>

		<form:form method="POST" action="/addQuestion">
			<center>
				<table align="center" width="80%">
					<tr>
						<td><h4>Question:</h4></td>
						<td colspan="2"><textarea name="qText" id="qText" rows="5"
								cols="130"></textarea></td>
					</tr>
					<tr>
						<td>
							<h4>Question Type:</h4>
						</td>

						<td colspan="2"><div class="styled-select">
								<select id="qnChoice" name="qnChoice" class="dropdown">
									<option value="1">Single Choice</option>
									<option value="2">Multiple Choice</option>
									<option value="3">True/False</option>
									<option value="4">Correct Order</option>
								</select>
							</div></td>

					</tr>
					<tr>
						<td><h4>Options:</h4></td>
						<td colspan="2">
							<div id='TextBoxesGroup'>
								<div id="TextBoxDiv1"></div>
								<br />
							</div> <input type='button' value='Remove' id='removeButton'> <input
							type='button' value='Add' id='addButton'>
						</td>

					</tr>

					<tr>
						<br />
						<td><h4>Best Answer:</h4></td>
						<td colspan="2"><input type="text" placeholder="e.g. AB"
							name="answer" /></td>
					</tr>
					<tr>
						<br />
						<td colspan="3"><br />
							<div id="question">
								<br />
								<center>
									<input type="hidden" name="options" id="options" value="" /> <input
										class="myButton" type="submit" name="submitQuestion"
										id="submitQuestion" onclick="manageOption(this.form);"
										value="Add Question"> <input class="myButton"
										type="reset" name="resetButton" id="resetButton"
										onclick="reloadPage();" value="Reset" />
								</center>
							</div></td>

					</tr>

				</table>
			</center>
		</form:form>

	</div>
	</center>
	<tr>
		<div>
			<form method="post" action="/redirect">
				<input type="hidden" name="name" value="${teacherName }"></input> <input
					type="hidden" name="password" value="${teacherPassword}"></input> <input
					type="submit" value=">> Main Page" align="right"></input>
			</form>
		</div>
	</tr>
	<br>


	<script type="text/javascript">
		$(document)
				.ready(
						function() {

							var counter = 1;

							$("#addButton")
									.click(
											function() {

												if (counter > 10) {
													alert("Only 10 textboxes allow");
													return false;
												}
												var fruits = [ "", "A", "B",
														"C", "D", "E", "F",
														"G", "H", "I", "J" ];
												var newTextBoxDiv = $(
														document
																.createElement('div'))
														.attr(
																"id",
																'TextBoxDiv'
																		+ counter);

												newTextBoxDiv
														.after()
														.html(
																'<tr><td><h4><label>'
																		+ fruits[counter]
																		+ '. </label></h4></td> '
																		+ '<td><textarea  name="textbox'
																		+ counter
																		+ '" id="textbox'
																		+ counter
																		+ '" value="" rows="2" cols="100"></textarea></td></tr>');

												newTextBoxDiv
														.appendTo("#TextBoxesGroup");

												counter++;
											});

							$("#removeButton").click(function() {
								if (counter == 2) {
									alert("At least 1 options is required.");
									return false;
								}

								if (counter > 2) {
									counter--;
								}

								$("#TextBoxDiv" + counter).remove();

							});

							$("#getButtonValue").click(
									function() {

										var msg = '';
										for (i = 1; i < counter; i++) {
											msg += "\n Textbox #" + i + " : "
													+ $('#textbox' + i).val();
										}
										alert(msg);
									});
						});
	</script>


</body>




</html>