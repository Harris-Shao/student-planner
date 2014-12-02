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
	
	private static final String INSERT_EVENT_DAIPLOT="INSERT INTO Event(UserID,IsOwner,StartTime,EndTime,Texts,EValue, Resources)"
			+ "	VALUES (?,?,?,?,?,?,?)";

	private static final String INSERT_EVENT="INSERT INTO Event(UserID,Title,DateStr,TimeStr,Address,Tag) VALUES (?,?,?,?,?,?)";
	private static final String DELETE_EVENT_BY_ID="DELETE FROM Event WHERE id=%s"; 
	private static final String SELECT_EVENT_BY_TAG="SELECT * FROM Event WHERE UserID=? AND Tag=?";
	private static final String UPDATE_EVENT_BY_ID="UPDATE Event SET Title=?, DateStr=?, TimeStr=?, Address=?, Tag=? "+
											 "WHERE id=?";
	private static final String SELECT_ALL_EVENTS_FOR_USER="SELECT * FROM Event WHERE UserID=?";
	
	public EventDataManager() {
		
	}
	
	//Modify event by id
	public static boolean update(int id,String title,String date,String time,String address,String tag)
	{
		Connection con=null;
		boolean success=false;
		PreparedStatement ps=null;
		
		try
		{
			con=DBConnectionManager.getConnection();
			ps=con.prepareStatement(UPDATE_EVENT_BY_ID);
			ps.setString(1,title);
			ps.setString(2,date);
			ps.setString(3, time);
			ps.setString(4, address);
			ps.setString(5, tag);
			ps.setInt(6,id);
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
			ps=con.prepareStatement(SELECT_ALL_EVENTS_FOR_USER);
			ps.setInt(1, userID);
		
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				Event event=new Event();
				event.setId(rs.getInt("ID"));
				event.setEnd(rs.getString("EndTime"));
				event.setStart(rs.getString("StartTime"));
				event.setOwner(rs.getBoolean("IsOwner"));
				event.setUserID(rs.getInt("UserID"));
				event.setValue(rs.getString("EValue"));
				event.setResources(rs.getString("Resources"));
				event.setText(rs.getString("Texts"));
				events.add(event);
			}
		}	
		catch(SQLException | IOException e)
		{
				e.printStackTrace();
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
				e.printStackTrace();
			}
					
		}
		
		return events;
	}

	//select event by tag
/*	public static List<Event> getUserEventsByTag(int userID,String queryTag)
	
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
				event.setDateStr(rs.getString("DateStr"));
				event.setTimeStr(rs.getString("TimeStr"));
				event.setTitle(rs.getString("Title"));
				event.setTag(rs.getString("Tag"));
				event.setUserID(rs.getInt("UserID"));
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
	*/
	//insert a new event
/*	public static boolean insert(Event event)
	{
		Connection con=null;
		PreparedStatement ps=null;
		boolean success=false;
		
		try
		{
			con=DBConnectionManager.getConnection();
			ps=con.prepareStatement(INSERT_EVENT);
			ps.setInt(1, event.getUserID());
			ps.setString(2, event.getTitle());
			ps.setString(3, event.getDateStr());
			ps.setString(4, event.getTimeStr());
			ps.setString(5, event.getAddress());
			ps.setString(6, event.getTag());
		
			int rowCount=ps.executeUpdate();
			
			if(rowCount==1)
			{
				success=true;
				con.commit();
			}
		}	
		catch(SQLException | IOException e)
		{
				e.printStackTrace();
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
				e.printStackTrace();
			}
					
		}
		
		
		return success;
	}
	*/
	
	public static boolean insertDAIEvent(Event event)
	{
		Connection con=null;
		PreparedStatement ps=null;
		boolean success=false;
		try
		{
			con=DBConnectionManager.getConnection();
			ps=con.prepareStatement(INSERT_EVENT_DAIPLOT);
			ps.setInt(1, event.getUserID());
			ps.setBoolean(2, event.isOwner());
			ps.setString(3, event.getStart());
			ps.setString(4, event.getEnd());
			ps.setString(5, event.getText());
			ps.setString(6, event.getValue()==null?"":event.getValue());
			ps.setString(7, event.getResources());
		
			int rowCount=ps.executeUpdate();
			
			if(rowCount==1)
			{
				success=true;
				con.commit();
			}
		}	
		catch(SQLException | IOException e)
		{
				e.printStackTrace();
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
				e.printStackTrace();
			}
					
		}
		
		
		return success;
	}

	public static boolean shareEvent(Event event, int userID)
	{
		Connection con=null;
		PreparedStatement ps=null;
		boolean success=false;
		try
		{
			con=DBConnectionManager.getConnection();
			ps=con.prepareStatement(INSERT_EVENT_DAIPLOT);
			ps.setInt(1, userID);
			ps.setBoolean(2, false);
			ps.setString(3, event.getStart());
			ps.setString(4, event.getEnd());
			ps.setString(5, event.getText());
			ps.setString(6, event.getValue());
			ps.setString(7, event.getResources());
		
			int rowCount=ps.executeUpdate();
			
			if(rowCount==1)
			{
				success=true;
				con.commit();
			}
		}	
		catch(SQLException | IOException e)
		{
				e.printStackTrace();
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
				e.printStackTrace();
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
			e.printStackTrace();
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
				e.printStackTrace();
			}
					
		}
		
		
		return success;
	}
	
	//
}
