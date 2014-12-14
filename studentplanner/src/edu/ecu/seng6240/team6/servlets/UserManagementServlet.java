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
		List<String> errorList = new ArrayList<>();
		if (action == null) {
			responseCode = Response.SC_BAD_REQUEST;
			errorList.add("invalid request");
		}
		else {
			if (action.equals("add")){
				
				JsonObject dataObj = (new JsonParser().parse(RequestHelper.getDataString(request))).getAsJsonObject();
				User student = null;
				String userName = dataObj.get("username").getAsString();
				String firstName = dataObj.get("firstname").getAsString();
				String lastName = dataObj.get("lastname").getAsString();
				String password1 = dataObj.get("password1").getAsString();
				String password2 = dataObj.get("password2").getAsString();
				String value = dataObj.get("value").getAsString();
				String answer = dataObj.get("answer").getAsString();
				String email = dataObj.get("email").getAsString();
				if (password1 != null && password2 != null && password1.trim().length() != 0 
						&& password2.trim().length() != 0 && password1.trim().equals(password2.trim()) ){
					//valid password, good, move one
					if (answer != null && answer.trim().equals(value)) {
						//correct answer
						if ( userName != null && email != null) {
							//valid user credentials
							student = new User();
							student.setFirstName(firstName);
							student.setLastName(lastName);
							student.setPassword(password1.trim());
							student.setUserEmail(email);
							student.setUserName(userName);
							boolean insertSuccess = UserDataManager.insert(student);
							
							if (insertSuccess) {
								responseCode = Response.SC_OK;
								SessionManager sessionManager = new SessionManager(request);
								sessionManager.setUser(student);
							}
							else{
								errorList.add("insert into DB error: username or email taken");
							}
						}
						else {
							errorList.add("username or email null");
						}
						
					}
					else 
					{
						errorList.add("math incorrect!");
					}
				}
				else{
					errorList.add("password problems");
				}
			}
			else if (action.equals("updatePassword")){
								
				JsonObject dataObj = (new JsonParser().parse(RequestHelper.getDataString(request))).getAsJsonObject();
				String userName = dataObj.get("username").getAsString();
				String password1 = dataObj.get("password1").getAsString();
				String password2 = dataObj.get("password2").getAsString();
				String value = dataObj.get("value").getAsString();
				String answer = dataObj.get("answer").getAsString();
				String email = dataObj.get("email").getAsString();
				if (password1 != null && password2 != null && password1.trim().length() != 0 
						&& password2.trim().length() != 0 && password1.trim().equals(password2.trim()) ){
					//valid password, good, move one
					if (answer != null && answer.trim().equals(value)) {
						//correct answer
						if ( userName != null && email != null) {
							//valid user credentials
							boolean hasStudent = UserDataManager.hasUser(userName, email);
							
							if (hasStudent) {
								
								boolean updateStudentPassword = UserDataManager.updatePassword(email, password1);
								if (updateStudentPassword)
								{
									responseCode = Response.SC_OK;
								}
								else
								{
									errorList.add("update DB error: failed to store password..try again");
								}

							}
							else{
								errorList.add("update DB error: no user with username and email");
							}
						}
						else {
							errorList.add("username or email null");
						}
						
					}
					else 
					{
						errorList.add("math incorrect!");
					}
				}
				else{
					errorList.add("password problems");
				}
			}
			else if (action.equals("delete")){
				String idString = request.getParameter("id");
				int id = Integer.parseInt(idString);
				boolean deleteSuccess = UserDataManager.deleteStudent(id);
				if (deleteSuccess) {
					responseCode = Response.SC_OK;
				}else {
					errorList.add("remove user error");
				}
			}
			else if (action.equals("getAll")){
				responseCode= Response.SC_OK;
			}
			else {
				responseCode = Response.SC_BAD_REQUEST;
				errorList.add("invalid request");
			}
			
		}
		response.setStatus(responseCode);
		
		JsonArray errors= (JsonArray) new Gson().toJsonTree(errorList);
		
		JsonObject responseObject = new JsonObject();
		responseObject.addProperty("status",response.getStatus());
		responseObject.add("errors", errors);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.getWriter().write(gson.toJson(responseObject));

	}

}
