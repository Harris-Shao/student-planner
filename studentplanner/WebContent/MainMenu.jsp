<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="java.util.List" %>
<%@ page import="edu.ecu.seng6240.team6.models.*, edu.ecu.seng6240.team6.Helper.*" %>
<%@ page import="com.google.gson.*" %>
<% 
	SessionManager sessionManager = new SessionManager(request);
	JsonArray events = null;
	boolean render = false;
	User user = null;
	int userID = -1;
	if (!sessionManager.hasUser()) {
	 	response.sendRedirect("/login.jsp");
	}
	else 
	{
		user = sessionManager.getUser();
		System.out.println(user.getId());

		if (user.getId() == -1) {
			System.out.println("= -1");
			user = UserDataManager.getStudentByUserName(user.getUserName());
			System.out.println(user.getUserName());
			sessionManager.setUser(user);
			userID = user.getId();
			System.out.println(userID);
		}
		List<Event> eventList = EventDataManager.getAllUserEvents(user.getId());
		events = new JsonArray();
		for (Event e:eventList) {
			events.add(e.toJsonObject());
		}
		render = true;
	}
	if (render){
%>
 
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <script src="js/daypilot-all.min.js" type="text/javascript"></script>
    <script src="js/daypilot-modal-2.1.js" type="text/javascript"></script> 
    <link type="text/css" rel="stylesheet" href="css/calendar_green.css" />
    <link type="text/css" rel="stylesheet" href="css/calendar_transparent.css" />  
    <link type="text/css" rel="stylesheet" href="css/calendar_white.css" />     
</head>
<body>
	<button onClick="window.location='/login.jsp;'"><h2>logout</h2></button>
    <!-- CSS THEME MENU -->


    <div class="space">
        CSS Theme:
        <select id="theme">
            <option value="calendar_default">Default</option>
            <option value="calendar_green">Green</option>
            <option value="calendar_transparent">Transparent</option>
            <option value="calendar_white">White</option>
        </select>
    </div>

    <div style="float:left; width: 160px;">
        <div id="nav"></div>
    </div>
    <div style="margin-left: 160px;">
        <div id="dp"></div>
    </div>

    <script type="text/javascript">
		var eventList = <%= events %>;
		var userID = <%= user.getId() %>;
		console.log(userID);
         $(document).ready(function ($) {
            $("#theme").change(function (e) {
                dp.theme = this.value;
                dp.update();
            });
        });
    </script>



    <script type="text/javascript">

        // MONTHLY NAVIGATOR

        var nav = new DayPilot.Navigator("nav");
        nav.showMonths = 3;
        nav.selectMode = "week";
        nav.onTimeRangeSelected = function (args) {
            dp.startDate = args.start;
            dp.update();
        };
        nav.init();

    </script>

    
    <script type="text/javascript">

        // CALENDAR CREATION

        var dp = new DayPilot.Calendar("dp");
        dp.viewType = "Week";
        dp.heightSpec = "Fixed";
        dp.height = 600;  
        populateList(dp.events, eventList);
        
        
        dp.resources = [
                        { name: "A", id: "A" },
                        { name: "B", id: "B" },
                        { name: "C", id: "C" }
                      ];
        dp.init();
        
        //populate user event list
        
        function populateList(dpcevents, eventss){
        	
         var events = [];
	        for (var i = 0 ; i < eventss.length; i++){
	        	var eve = eventss[i];
	        	eve = {data:eve};
	        	console.log(eve);
	        	var e = new DayPilot.Event({start:new DayPilot.Date(eve.data.start), end:(new DayPilot.Date(eve.data.end)), value: eve.data.value, text: eve.data.text, resource:eve.data.resources, id:eve.data.id});
	        	dpcevents.add(e);
	        }
	        return events;
        }
        var user;

        // RIGHT_CLICK MENU FOR EVENTS

        dp.contextMenu = new DayPilot.Menu({
            items: [
            { text: "Share", onclick: function () { user = prompt("Share event with: ", "Email Address");console.log(this.source); shareEvent(this.source.data, user);}  },
            { text: "Show event ID", onclick: function () { alert("Event value: " + this.source.value()); } },
            { text: "Show event text", onclick: function () { alert("Event text: " + this.source.text()); } },
            { text: "Show event start", onclick: function () { alert("Event start: " + this.source.start().toStringSortable()); } },
            { text: "Show event stop", onclick: function () { alert("Event stop: " + this.source.end().toStringSortable()); } },
            { text: "Delete", onclick: function () { dp.events.remove(this.source); console.log(this.source.data.id); deleteEvent(this.source.data.id); } }
            ]
        });

      


        // EVENT CREATION
        // on-click; use drag to stretch event. Can move event around.

        dp.onTimeRangeSelected = function (args) {  
        	console.log(args);
            var name = prompt("New event name:", "Event");
            dp.clearSelection();
            if (!name) return;
            var e = new DayPilot.Event({
                start: args.start,
                end: args.end,
                id: DayPilot.guid(),
                resource: args.resource,
                text: name
            });
            dp.events.add(e);
            //call back end service
            addEvent(e);
      
         	console.log(e);
            
            dp.message("Created");
        };
        
		
        function addEvent(event) {
			var eve = {
					userID:userID,
					text:event.data.text,
					start:event.data.start.value,
					end:event.data.end.value,
					resources:event.resource,
					value:" ",
			};
			var dataObj = {
					event:eve
			};
			console.log(dataObj);
			$.ajax({
				type : 'POST', 
				url : "/EventServlet?action=add",
				data: JSON.stringify(dataObj),
				processData:false,
				contentType:'application/json',
				mimeType: 'text/plain; charset=x-user-defined',
				dataType:"json",
				success : function(data) 
				{
						console.log()
						window.location = "/MainMenu.jsp";
	
				},
				error : function(data) 
				{
					console.log(data);
					if (data.responseJSON && data.responseJSON.errors) {
						alert(data.responseJSON.errors)
					}
					else {
						alert("bad request");
					}
				}
			});
		}

        
        function deleteEvent(id) {
			$.ajax({
				type : 'POST', 
				url : "/EventServlet?action=delete&id="+id,
				processData:false,
				contentType:'application/json',
				mimeType: 'text/plain; charset=x-user-defined',
				dataType:"json",
				success : function(data) 
				{
	
						window.location = "/MainMenu.jsp";
	
				},
				error : function(data) 
				{
					console.log(data);
					if (data.responseJSON.errors) {
						alert(data.responseJSON.errors)
					}
					else {
						alert("bad request");
					}
				}
			});
		}
        
        function shareEvent(event, label) {
			var event = {
					text:event.text,
					value:event.value,
					start:event.start.value,
					end:event.end.value,
					resources:event.resource,
					value:event.value,
					
			};
			
			console.log(event);
			
			var dataObj = {event:event, 
					username:label,
					email:label
						};
			
			$.ajax({
				type : 'POST', 
				url : "/EventServlet?action=share",
				processData:false,
				data: JSON.stringify(dataObj),

				contentType:'application/json',
				mimeType: 'text/plain; charset=x-user-defined',
				dataType:"json",
				success : function(data) 
				{
	
						window.location = "/MainMenu.jsp";
	
				},
				error : function(data) 
				{
					console.log(data);
					if (data.responseJSON && data.responseJSON.errors) {
						alert(data.responseJSON.errors)
					}
					else {
						alert("bad request");
					}
				}
			});
		}
        
    </script>
   
   

    <!--nav.onTimeRangeSelected = function(args) {
    dp.startDate = args.day;
    dp.update();
    loadEvents();
    };-->


    

    </body>
    </html>
<%
}
%>