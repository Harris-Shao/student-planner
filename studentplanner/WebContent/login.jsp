<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login</title>
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    <style>body {background-color: black;}</style> 
</head>
<body>

    <div id="login-overlay" class="modal-dialog model-content">
        <div class="modal-header">
            <h4 id="myModalLabel" class="modal-title" style="color:white"> Welcome! Please login to your Student Planner</h4>
        </div>
        <div class="modal-body row col-sm-6 col-sm-offset-3 well">
            <form id="loginForm" method="POST" action="/login/" novalidate="novalidate">
                <div class="form-group">
                    <label for="username" class="control-label" style="color:white">Username</label>
                    <input type="text" class="form-control" id="username" name="username"
                           value="" required="" title="Please enter you username">
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label for="password" class="control-label" style="color:white">Password</label>
                    <input type="password" class="form-control" id="password"
                           name="password" value="" required="" title="Please enter your password">
                    <span class="help-block"></span>
                </div>
                <div id="loginErrorMsg" class="alert alert-error hide" style="color:white">Wrong username or password</div>
                <div class="checkbox">
                    <label> <input type="checkbox" name="remember" id="remember" style="color:white"> Remember login</label>
                </div>
                <button type="submit" class="btn btn-success btn-block">Login</button>
                <a href="/ResetPassword.jsp" class="btn btn-default btn-block">Forgot Username/Password</a>
                <a href="/Registration.jsp" class="btn btn-info btn-block">Sign up here!</a>
            </form>
        </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>
