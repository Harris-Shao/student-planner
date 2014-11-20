<%@ page language="java" contentType="text/html; charset=US-ASCII"
pageEncoding="US-ASCII"%>
<%@ page import="edu.ecu.seng6240.team6.Helper.*" %>
<%@ page import="edu.ecu.seng6240.team6.models.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
/* SessionManager sessionManager = new SessionManager(request);
User user = sessionManager.getUser();
if (user == null){
response.sendRedirect("/NotAuthorized.jsp");
}
request.setAttribute("user", user);
pageContext.setAttribute("user", user); */
%>

<!DOCTYPE html>
<html>
<head>
    <title>Student Planner</title>
    <link rel="stylesheet" type="text/css" href="/css/MPstyle.css">
</head>
<body>
    <div id="container">    

        <div id="header"> <div style="position:absolute; left: 40%;"><img class="displayed" src="images\planner.jpg" width="500" height="320" alt="Planner" /></div> </div>     <!--image path might change; image subject to change-->

        <div id="content">
            <div style="position:absolute; left:45%;top:40%;"><a href="#" class="button">Schedule An Event</a></div>         <!--placeholder for href--> 
           
            <div style="position:absolute;left:45%;top:65%;"><a href="#" class="button">View Schedule</a></div>             <!--placeholder for href--> 
       
<!--             <c:choose>  
                <c:when test="${user.getRole() eq 'Student'}">
                <h1>Student Event Management</h1>
                </c:when>
                <c:otherwise>
                <h1>User Management</h1>
                </c:otherwise>
                </c:choose> -->
        </div>


        <div id="footer">&copy ECU SENG 6240 Team 6 - Fall 2014</div>

    </div>
</body>
</html>



<!-- To Do List
    
    Check pos for buttons, images. -->
