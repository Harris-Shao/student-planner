package tests.edu.ecu.seng6240.team6.helper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.ecu.seng6240.team6.Helper.EventDataManager;
import edu.ecu.seng6240.team6.models.Event;

public class test_EventDataManager {

	//@Test
	public void testInsertEvent() {
		
		int userId=100;
		Event event1=new Event(userId,"Meeting","08/10/2014","2:30 PM","Bates","academic");
		Event event2=new Event(userId,"Partayy","08/12/2014","3:00 AM","@John's","social");
		
		boolean success1=EventDataManager.insert(event1);
		boolean success2=EventDataManager.insert(event2);
		
		assertTrue(success1);
		assertTrue(success2);
	}

	//@Test 
	public void testModifyEvent()
	{
		int eventID=2;
		boolean success=EventDataManager.update(eventID, "Meeting", "08/10/2014", "2:30", "Bates", "academic");
		assertTrue(success);
				
		
	}
	
	//@Test
	public void testGetEvent()
	{
		int userId=100;
		Event expectEvent1=new Event(userId,"Meeting","08/10/2014","2:30 PM","Bates","academic");
		Event expectEvent2=new Event(userId,"Partayy","08/12/2014","3:00 AM","@John's","social");
		expectEvent1.setId(1);
		expectEvent2.setId(2);
		List<Event> expectEvents=new ArrayList<Event>();
		expectEvents.add(expectEvent1);
		expectEvents.add(expectEvent2);
		List<Event> resultAcademicEvents=EventDataManager.getUserEventsByTag(userId, "academic");
		
		List<Event> resultAllEvents=EventDataManager.getAllUserEvents(userId);
		
		assertTrue(expectEvent1.equals(resultAcademicEvents.get(0)));
		
		assertTrue(expectEvents.equals(resultAllEvents));
		
	}
	
	@Test
	public void deleteEvent()
	{
		
		int eventId=1;
		boolean success=EventDataManager.delete(eventId);
		assertTrue(success);
		
	}
}
