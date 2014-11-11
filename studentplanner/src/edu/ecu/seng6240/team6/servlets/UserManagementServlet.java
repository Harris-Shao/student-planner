package edu.ecu.seng6240.team6.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.google.gson.Gson;

import edu.ecu.seng6240.team6.Helper.RequestHelper;
import edu.ecu.seng6240.team6.Helper.UserDataManager;
import edu.ecu.seng6240.team6.models.Student;
import edu.ecu.seng6240.team6.models.User;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet("/UserManagementServlet")
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int responseCode = Response.SC_BAD_REQUEST;		
		String action = request.getParameter("action");
		if (action == null) {
			response.setStatus(responseCode);
		}
		else {
			if (action.equals("add")){
				Student student = null;
				String dataString = RequestHelper.getDataString(request);
				
				if (dataString != null) {
					student = new Gson().fromJson(dataString, Student.class);
					boolean insertSuccess = UserDataManager.insert(student);
					
					if (insertSuccess) {
						responseCode = Response.SC_OK;
					}				
				}
			}
			else if (action.equals("update")){
				Student student = null;
				String dataString = RequestHelper.getDataString(request);
				
				if (dataString != null) {
					student = new Gson().fromJson(dataString, Student.class);
					boolean updateSuccess = UserDataManager.update(student);
					if (updateSuccess)
					{
						responseCode  = Response.SC_OK;
					}					
				}
			}
			else if (action.equals("delete")){
				String idString = request.getParameter("id");
				int id = Integer.parseInt(idString);
				boolean deleteSuccess = UserDataManager.deleteStudent(id);
				if (deleteSuccess) {
					responseCode = Response.SC_OK;
				}
			}
			else if (action.equals("getAll")){
				List<User> users = UserDataManager.getAllUser();
				System.out.println(users.size());
				for (User user:users)
				{
					System.out.println(user.getFirstName());
				}
			}
			else {
				responseCode = Response.SC_BAD_REQUEST;
			}
			
		}
			response.setStatus(responseCode);
			
			RequestDispatcher rd = null;
			if (responseCode != 200) {
				rd = request.getRequestDispatcher("/BadRequest.jsp");
			}
			else {
				rd = request.getRequestDispatcher("/MainMenu.jsp");
			}
			rd.forward(request, response);
	}

}
