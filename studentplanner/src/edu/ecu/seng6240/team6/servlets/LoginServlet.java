package edu.ecu.seng6240.team6.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.google.gson.Gson;

import edu.ecu.seng6240.team6.Helper.RequestHelper;
import edu.ecu.seng6240.team6.Helper.SessionManager;
import edu.ecu.seng6240.team6.Helper.UserDataManager;
import edu.ecu.seng6240.team6.models.Student;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int responseCode = Response.SC_BAD_REQUEST;
		boolean retrieveSuccess=false;
		String action = request.getParameter("action");
		if (action == null) 
		{
			response.setStatus(responseCode);
		}
		else 
		{
			if (action.equals("login"))
			{
				
				responseCode=Response.SC_OK;
				
				//retrieve the userNameString from the request
				String userNameString = RequestHelper.getDataString(request);
				
				if (userNameString != null) 
				{
					//try to query the database for student with given username
					Student retrievedStd=UserDataManager.getStudentByUserName(userNameString);
				
					if(retrievedStd !=null)
					 {
						 //given user exists... so start a new session
						 SessionManager beginSession=new SessionManager(request);
						 beginSession.setUser(retrievedStd);
						 retrieveSuccess=true;
					 }
					 else
					 {
						 //user with given username doesn't exist
						 retrieveSuccess=false;
					 }
					
				}// end if(dataString !=null)
				
			}
			else 
			{
				//if (action not equals "login")
				responseCode=Response.SC_BAD_REQUEST;
				
			}//end if(action.equals("login"))

			
		}//end if(action == null)
		
		response.setStatus(responseCode);
		
		RequestDispatcher rd=null;
		
		if (responseCode != 200) {
			rd = request.getRequestDispatcher("/BadRequest.jsp");
		}
		else {
			if(retrieveSuccess)
				rd = request.getRequestDispatcher("/MainMenu.jsp");
			else
				rd=  request.getRequestDispatcher("/NotAuthorized.jsp");
		}
		rd.forward(request, response);

		
		
	}//end method
}
