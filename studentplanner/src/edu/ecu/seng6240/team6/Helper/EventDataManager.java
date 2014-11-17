package edu.ecu.seng6240.team6.Helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ecu.seng6240.team6.models.Event;

public class EventDataManager {

	private static final String INSERT_EVENT="INSERT INTO Event(UserID,Title,Date,Time,Address,Tag) VALUES (?,?,?,?,?,?)";
	private static final String DELETE_EVENT_BY_ID="DELETE FROM Event WHERE id=%s"; 
	private static final String SELECT_EVENT_BY_TAG="SELECT * FROM Event WHERE UserID=? AND Tag=?";
	private static final String UPDATE_EVENT_BY_ID="UPDATE Event SET Title=?, Date=?, Time=?, Address=?, Tag=? "+
											 "WHERE id=%s";
	private static final String SELECT_ALL_EVENTS="SELECT * FROM Event WHERE UserID=%s";
	public EventDataManager() {
		
	}
	
	//Modify event by id
	public static boolean update(int id)
	{
		Connection con=null;
		String updateString=String.format(UPDATE_EVENT_BY_ID,Integer.toString(id));
		PreparedStatement ps=null;
		boolean success=false;
		
		try
		{
			con=DBConnectionManager.getConnection();
			ps=con.prepareStatement(updateString);

			int rowCount=ps.executeUpdate();
			
			if(rowCount==1)
			{
				success=true;
				con.commit();
			}
		}	
		catch(SQLException | IOException e)
		{
				
		}
			
		finally
		{
			try
			{
				if (ps!=null)ps.close();
				if(con!=null)con.close();
			}
			catch (SQLException e)
			{
				
			}
					
		}
		
		
		return success;
	}

	//select all events by a user
	public static List<Event> getAllUserEvents(int userID)
	
	{
		List<Event> events=new ArrayList<Event>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			con=DBConnectionManager.getConnection();
			ps=con.prepareStatement(SELECT_ALL_EVENTS);
			ps.setInt(1, userID);
		
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				Event event=new Event();
				event.setId(rs.getInt("id"));
				event.setAddress(rs.getString("Address"));
				event.setDate(rs.getString("Date"));
				event.setTime(rs.getString("Time"));
				events.add(event);
			}
		}	
		catch(SQLException | IOException e)
		{
				
		}
			
		finally
		{
			try
			{
				if(rs!=null)rs.close();
				if (ps!=null)ps.close();
				if(con!=null)con.close();
			}
			catch (SQLException e)
			{
				
			}
					
		}
		
		return events;
	}

	//select event by tag
	public static List<Event> getUserEventsByTag(int userID,String queryTag)
	
	{
		List<Event> events=new ArrayList<Event>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			con=DBConnectionManager.getConnection();
			ps=con.prepareStatement(SELECT_EVENT_BY_TAG);
			ps.setInt(1, userID);
			ps.setString(2, queryTag);
		
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				Event event=new Event();
				event.setId(rs.getInt("id"));
				event.setAddress(rs.getString("Address"));
				event.setDate(rs.getString("Date"));
				event.setTime(rs.getString("Time"));
				events.add(event);
			}
		}	
		catch(SQLException | IOException e)
		{
				
		}
			
		finally
		{
			try
			{
				if(rs!=null)rs.close();
				if (ps!=null)ps.close();
				if(con!=null)con.close();
			}
			catch (SQLException e)
			{
				
			}
					
		}
		
		return events;
	}
	
	//insert a new event
	public static boolean insert(Event event)
	{
		Connection con=null;
		PreparedStatement ps=null;
		boolean success=false;
		
		try
		{
			con=DBConnectionManager.getConnection();
			ps=con.prepareStatement(INSERT_EVENT);
			ps.setString(1, event.getTitle());
			ps.setString(2, event.getDate());
			ps.setString(3, event.getTime());
			ps.setString(4, event.getAddress());
			ps.setString(5, event.getTag());
		
			int rowCount=ps.executeUpdate();
			
			if(rowCount==1)
			{
				success=true;
				con.commit();
			}
		}	
		catch(SQLException | IOException e)
		{
				
		}
			
		finally
		{
			try
			{
				if (ps!=null)ps.close();
				if(con!=null)con.close();
			}
			catch (SQLException e)
			{
				
			}
					
		}
		
		
		return success;
	}
	
	//delete Event by id
	public static boolean delete(int id)
	{
		Connection con=null;
		String delString=String.format(DELETE_EVENT_BY_ID,Integer.toString(id));
		PreparedStatement ps=null;
		boolean success=false;
		
		try
		{
			con=DBConnectionManager.getConnection();
			ps=con.prepareStatement(delString);

			int rowCount=ps.executeUpdate();
			
			if(rowCount==1)
			{
				success=true;
				con.commit();
			}
		}	
		catch(SQLException | IOException e)
		{
				
		}
			
		finally
		{
			try
			{
				if (ps!=null)ps.close();
				if(con!=null)con.close();
			}
			catch (SQLException e)
			{
				
			}
					
		}
		
		
		return success;
	}
	
	//
}
