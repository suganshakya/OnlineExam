<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Knowledge Check Online</title>
</head>
<body>
	<div id="kn-check">
		<h1>Online Examination</h1>
	</div>

	<div id="bg-table">
		<form method="post" action="/redirect">
			<table>
				<tbody>
					<tr>
						<h4>Teacher Login:</h4>
					</tr>
					<hr>
					<tr>
						<td><h4>
								<label>User Id</label>
							</h4></td>
						<td><h4>
								<label>Password</label>
							</h4></td>
					</tr>

					<tr>
						<td><input type="text" name="name"></td>
						<td><input type="password" name="password"></td>
						<td><input type="submit" class="form-submit" name="Log"
							value="Log In"></td>
					</tr>
					<tr>
						<td><h4>
								<label>${msg }</label>
							</h4></td>

						<br />
					<tr>

					</tr>
				</tbody>
			</table>

		</form>
	</div>

	<br>
	<center>
		<table width="90%">
			<tr>
				<td>
					<div>
						<center>
							<h1 style="text-shadow: 2px 2px 2px #282828; color: #ffffff">
								Accenture Java Boot-Camp</h1>
							<br />
							<h2 style="text-shadow: 2px 2px 2px #282828; color: #ffffff">Accenture,
								Latvia</h2>
							<br />


						</center>
					</div>
					<div>
						<p>
						<center>
							<a href="pages/student_register.jsp" target=""><h3>Register</h3></a>
						</center>
						</p>
					</div>
				</td>
				<td>
					<div id="student-div">
						<form class="form_login" action="/goStudent" method="post">
							<section class="container">
							<div class="login">
								<center>
									<h2>Student Login</h2>
									<br />
									<p>
										<input type="text" name="firstName" value=""
											placeholder="First Name">
									</p>
									<p>
										<input type="text" name="lastName" value=""
											placeholder="Last Name">
									</p>
									<p class="submit">
										<input type="submit" name="submitName" value="Start My Test">
									</p>
									<br />
									<h4>
										<label>${errmsg }</label>
									</h4>
									
								</center>
							</div>
							</section>
						</form>
					</div>
				</td>
			</tr>
		</table>
</body>
</center>
</html>