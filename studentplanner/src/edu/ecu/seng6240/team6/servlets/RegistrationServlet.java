package edu.ecu.seng6240.team6.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/UserRegistrationServlet")
public class RegistrationServlet 
{
	private static final long serialVersionUID=1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action=request.getParameter("action");
		RequestDispatcher rd=null;
		if( action== null || !action.equals("add"))
		{
			//if action != "add" then redirect request to bad request page
			rd=request.getRequestDispatcher("/BadRequest.jsp");
			rd.forward(request, response);
			
		}
		else
		{
			//pass the control to user management servlet, where the new
			//user will be added to the system. The user will also be
			//directed to the "/MainMenu.jsp" page at the end of this
			//request by the userManager.
			UserManagementServlet userManager=new UserManagementServlet();
			userManager.doPost(request, response);
			
		}//end if (action != "add")
		
	}
}
