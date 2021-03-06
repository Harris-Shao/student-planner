<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%
	request.getSession().setAttribute("User", null);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login</title>
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
        <![endif]-->
<style>
body {
	background-color: black;
}
</style>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script>
		$(document).ready(function(){
			
			$(".loginCredential").change(function(){
				if ($(this).val() !=null && $(this).val().trim().length !== 0)
				{
					$("#loginErrorMsg").addClass("hide");
				}
				else
				{
					$("#loginErrorMsg").removeClass("hide");
				}
				
			});
			
			
			
			
			$("button[type='submit']").click(function(){
				var credentialObject = {
						username:$("#username").val(),
						password:$("#password").val()				
				};
				$.ajax({
					type : 'POST', 
					url : "/LoginServlet?action=login",
					data: JSON.stringify(credentialObject),
					processData:false,
					contentType:'application/json',
					mimeType: 'text/plain; charset=x-user-defined',
					dataType:"json",
					success : function(data) 
					{
							console.log(data);
							window.location = "/MainMenu.jsp";

					},
					error : function(data) 
					{
						$("#loginErrorMsg").removeClass("hide");
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

	<div id="login-overlay" class="modal-dialog model-content">
		<div class="modal-header">
			<h4 id="myModalLabel" class="modal-title" style="color: white">
				Welcome! Please login to your Student Planner</h4>
		</div>
		<div class="modal-body row col-sm-6 col-sm-offset-3 well">
			<form id="loginForm" onsubmit="return mySubmitFunction();" novalidate="novalidate">
				<div class="form-group">
					<label for="username" class="control-label" style="color: black">Username or email</label>
					<input type="text" class="form-control loginCredential" id="username"
						name="userName" value="" required
						title="Please enter you username"> <span
						class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="password" class="control-label" style="color: black">Password</label>
					<input type="password" class="form-control loginCredential" id="password"
						name="password" value="" required
						title="Please enter your password"> <span
						class="help-block"></span>
				</div>
				<div id="loginErrorMsg" class="alert alert-error hide"
					style="color: black; font-weight:bold;color:red">Incorrect username or password</div>
				<div class="checkbox">
					<label> <input type="checkbox" name="remember"
						id="remember" style="color: black"> Remember login
					</label>
				</div>
				<button type="submit" class="btn btn-success btn-block">Login</button>
				<a href="/ResetPassword.jsp" class="btn btn-default btn-block">Forgot
					Username/Password</a> <a href="/Registration.jsp"
					class="btn btn-info btn-block">Sign up here!</a>
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
