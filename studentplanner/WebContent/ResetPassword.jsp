<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Reset password</title>
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]--->

    <style> body {background-color: black;}</style>
</head>
<body>
	<div id="login-overlay" class="modal-dialog modal-content">
        <div class="modal-header">
            <h4 class="modal-title" id="myModalLabel" style="color:black">Create a new account</h4>
        </div>
        <div class="modal-body row col-sm-6 col-sm-offset-3 well">
            <form id="registrationForm" method="POST" action="/UserManagementServlet?action=resetPassword" novalidate="novalidate">
                <div class="form-group">
                    <label for="email" class="control-label" style="color:black">E-Mail Address</label> 
                    <input type="text" class="form-control" id="email" name="email" value="" required="" title="Please enter your email address"> 
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label for="username" class="control-label" style="color:black">Username</label> 
                    <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please choose you username">
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label for="password" class="control-label" style="color:black">Password</label> 
                    <input type="password" class="form-control" id="password" name="password1" value="" required="" title="Please choose your password"> 
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label for="password" class="control-label" style="color:black">Reenter-Password</label> 
                    <input type="password" class="form-control" id="password" name="password2" value="" required="" title="Please reenter your password"> 
                    <span class="help-block"></span>
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