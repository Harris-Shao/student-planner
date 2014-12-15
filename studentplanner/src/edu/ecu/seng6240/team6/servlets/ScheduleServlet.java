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

import edu.ecu.seng6240.team6.Helper.EventDataManager;
import edu.ecu.seng6240.team6.Helper.RequestHelper;
import edu.ecu.seng6240.team6.Helper.UserDataManager;
import edu.ecu.seng6240.team6.models.Event;
import edu.ecu.seng6240.team6.models.User;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/EventServlet")
public class ScheduleServlet extends HttpServlet {
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
		String action = request.getParameter("action");
		List<String> errorList = new ArrayList<>();
		if (action == null) {
			responseCode = Response.SC_BAD_REQUEST;
			errorList.add("invalid request");
		}
		else {
			if (action.equals("add")){
				
				JsonObject dataObj = (new JsonParser().parse(RequestHelper.getDataString(request))).getAsJsonObject();
				Event event = new Event(dataObj.get("event").getAsJsonObject());
				event.setOwner(true);
				if (EventDataManager.insertDAIEvent(event)) {
					responseCode = Response.SC_OK;
				}
				else {
					errorList.add("add event failure");
				}
				
			}
			else if (action.equals("share")){
				JsonObject dataObj = (new JsonParser().parse(RequestHelper.getDataString(request))).getAsJsonObject();
				Event event = new Event(dataObj.get("event").getAsJsonObject());
				String username = dataObj.has("username")?dataObj.get("username").getAsString():null;
				String email = dataObj.has("email")?dataObj.get("email").getAsString():null;
				//
				User user= email != null? UserDataManager.getStudentByUserEmail(email):null;
				User user1 = username != null? UserDataManager.getStudentByUserName(username):null;
				
				if (user!= null){
					if (EventDataManager.shareEvent(event, user.getId())) {
						responseCode = Response.SC_OK;
					}
					else
					{
						errorList.add("share event failure : " + user.getUserEmail());
					}
				}
				else {
					errorList.add(" user not exist : " + email);
				}
				if (user1!= null){
					if (EventDataManager.shareEvent(event, user1.getId())) {
						responseCode = Response.SC_OK;
					}
					else
					{
						errorList.add("share event failure : " + user1.getUserName());
					}
				}
				else
				{
					errorList.add(" user not exist : " + username);

				}
			}
			else if (action.equals("delete")){
				String idString = request.getParameter("id");
				int id = Integer.parseInt(idString);
				boolean deleteSuccess = EventDataManager.delete(id);
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
