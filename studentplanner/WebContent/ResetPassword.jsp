<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="edu.ecu.seng6245.*, java.util.Random"%>
<%
	boolean render = true;
	Random random = new Random();
	int number1 = random.nextInt(20);
	int number2 = random.nextInt(20);
	int mathSymbol = random.nextInt(3);
	String math = null;
	if (mathSymbol==0) {
		math = " + ";
	}
	else if (mathSymbol ==1){
		math = " - ";
	}
	else {
		math = " x ";
	}
	
	String formular = number1 + math + number2;
	
	
	long value = 0;
	
	try {
		value = PostFix.calculate(formular);
	}
	catch (Exception e)
	{
		response.sendRedirect("/ResetPassword.jsp");
		render = false;
	}
			
	
	String valueString = Long.toString(value);
	
	if (render)
	{
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Reset password</title>
<!-- Bootstrap -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/styles.css" rel="stylesheet">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]--->

<style>
body {
	background-color: black;
}
</style>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script>
		$(document).ready(function(){
			
			$("button[type='submit']").click(function(){
				console.log("in submitting");
				var credentialObject = {
						username:$("#username").val(),
						password1:$("#password1").val(),
						password2:$("#password2").val(),
						email:$("#email").val(),
						value:$("#mathValue").val(),
						answer:$("#mathAnswer").val()				
				};
				console.log(credentialObject);
				$.ajax({
					type : 'POST', 
					url : "/UserManagementServlet?action=updatePassword",
					data: JSON.stringify(credentialObject),
					processData:false,
					contentType:'application/json',
					mimeType: 'text/plain; charset=x-user-defined',
					dataType:"json",
					success : function(data) 
					{

							window.location = "/login.jsp";

					},
					error : function(data) 
					{
						console.log(data);
						if (data.responseJSON.errors) {
							alert(data.responseJSON.errors)
						}
						else {
							alert("bad request");
						}
					}
				});
			});

			
		});

		function mySubmitFunction()
		{
		  return false;
		}
	</script>

</head>
<body>
	<div id="login-overlay" class="modal-dialog modal-content">
		<div class="modal-header">
			<h4 class="modal-title" id="myModalLabel" style="color: black">Go to <a href="/login.jsp">Login </a>if you remember your password</h4>
			<hr/>
			<h4 class="modal-title" id="myModalLabel" style="color: black">Reset your password</h4>
		</div>
		<div class="modal-body row col-sm-6 col-sm-offset-3 well">
			<form id="registrationForm" onsubmit="return mySubmitFunction()"
				novalidate="novalidate">
				<div class="form-group">
					<label for="email" class="control-label" style="color: black">E-Mail
						Address</label> <input type="text" class="form-control" id="email"
						name="email" value="" required
						title="Please enter your email address"> <span
						class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="username" class="control-label" style="color: black">Username</label>
					<input type="text" class="form-control" id="username"
						name="username" value="" required
						title="Please choose you username"> <span
						class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="password" class="control-label" style="color: black">Password</label>
					<input type="password" class="form-control" id="password1"
						name="password1" value="" required
						title="Please choose your password"> <span
						class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="password" class="control-label" style="color: black">Reenter-Password</label>
					<input type="password" class="form-control" id="password2"
						name="password2" value="" required
						title="Please reenter your password"> <span
						class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="answer" class="control-label" style="color: black">Result
						of <%=formular%> = ?
					</label> <input type="text" class="form-control" id="mathAnswer"
						name="answer" value="" required
						title="Please enter your answer"> <span class="help-block"></span>
				</div>
				<div class="form-group" style="display: none">
					<label for="value" class="control-label" style="color: black">Result
						of <%=formular%> = ?
					</label> <input type="text" class="form-control" id="mathValue"
						name="value" value="<%=valueString%>"> <span
						class="help-block"></span>
				</div>
				<button type="submit" class="btn btn-success btn-block">Submit</button>
			</form>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>
<% 
} 
%>