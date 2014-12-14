package edu.ecu.seng6240.team6.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.ecu.seng6240.team6.Helper.RequestHelper;
import edu.ecu.seng6240.team6.Helper.SessionManager;
import edu.ecu.seng6240.team6.Helper.UserDataManager;
import edu.ecu.seng6240.team6.models.User;

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
		List<String> errorList = new ArrayList<>();
		int responseCode = Response.SC_BAD_REQUEST;
		String action = request.getParameter("action");
		if (action == null) 
		{
			responseCode = Response.SC_BAD_REQUEST;
		}
		else 
		{
			if (action.equals("login"))
			{
				
				responseCode=Response.SC_OK;
				
				//retrieve the userNameString from the request
				
				JsonObject dataObj = (new JsonParser().parse(RequestHelper.getDataString(request))).getAsJsonObject();
				String userName = dataObj.get("username").getAsString();
				String userEmail = dataObj.get("username").getAsString();
				String password = dataObj.get("password").getAsString();
				
				
				User user = null;
				if (userEmail != null) {
					user = UserDataManager.getStudentByUserNameOrEmailAndPassword(userEmail, password);
				}
				if (user == null) {
					if (userName != null) {
						user = UserDataManager.getStudentByUserNameOrEmailAndPassword(userName, password);
					}
				}

				if (user != null) 
				{
					//given user exists... so start a new session
						 SessionManager beginSession=new SessionManager(request);
						 beginSession.setUser(user);
						 responseCode = Response.SC_OK;
				}
				else
				{
						 //user with given username doesn't exist
						 responseCode = Response.SC_FORBIDDEN;
				}
									
			}
			else 
			{
				//if (action not equals "login")
				responseCode=Response.SC_BAD_REQUEST;
				
			}//end if(action.equals("login"))

			
		}//end if(action == null)
		
		response.setStatus(responseCode);
				
		JsonArray errors= (JsonArray) new Gson().toJsonTree(errorList);
		
		JsonObject responseObject = new JsonObject();
		responseObject.addProperty("status",response.getStatus());
		responseObject.add("errors", errors);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.getWriter().write(gson.toJson(responseObject));
	
		
	}//end method
}
