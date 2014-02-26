/**
 * Handles methods for modifying and getting schedule objects
 * 
 * @author Simon Planhage
 * @version 2014-02-25
 */


package model;

import java.util.ArrayList;
import org.joda.time.DateTime;


public class ScheduleHandler {
	
	//A reference to the schedule object that contains all weeks
	static Schedule userSchedule;
	
	//A reference to the currently active week
	//OBSOBOSBOSBOSBOS GLÖM EJ ÄNDRA TILL PRIVATE OOBSOBOSBOS
	public Week currentWeek;
		
	//The current real week
	DateTime d = new DateTime();
	private int currentRealWeekNr = (new DateTime()).getWeekOfWeekyear();
	
	//The number of the week that is currently active and chosen with getWeek methods etc. Subtracts 1 to compensate for lists starting at 0.
	private int currentSelectedWeekNr = currentRealWeekNr - 1;
	
	//Don't know if this is needed yet
	@SuppressWarnings("unused")
	private int personNummer;
	
	
	
	
	/**
	 * Constructor who loads an existing schedule file, and puts the current weeks schedule as active. Some test code remains
	 * @param personNummer The personnummer of the currently logged in user
	 */
	public ScheduleHandler(int personNummer) {
		this.personNummer = personNummer;
		
		//Här ska en metod finnas för att hämta användarens schema-objekt från servern
		userSchedule = new Schedule();

		populateYear();
		currentWeek = userSchedule.weekList.get(currentRealWeekNr);
		System.out.println(currentWeek.weekNr);
		
	}

	
	/**
	 * Returns the schedule for the requested week
	 * @param weekNumber
	 * @return
	 */
	void getWeek(int weekNumber) {
		currentSelectedWeekNr = weekNumber;
		currentWeek = userSchedule.weekList.get(weekNumber);
	}

	
	void getNextWeek() {
		currentSelectedWeekNr++;
		currentWeek = userSchedule.weekList.get(currentSelectedWeekNr);
		System.out.println("Currently active week: " + currentWeek.weekNr);
	}

	void getPrevWeek() {
		currentSelectedWeekNr--;
		currentWeek = userSchedule.weekList.get(currentRealWeekNr);
		System.out.println("Currently active week: " + currentWeek.weekNr);
	}


	void modifySchedule() {
		
	}
	
	
	/**
	 * CheckIn on the current day and time
	 * TODO skall göras snyggare, inte prio just nu
	 * Creates a DateTime object for easy handling of current day and time.
	 * Uses the current day of the week to get the right day from the list of weekdays,
	 * then sets that days checkInTime to the time where the method is called.
	 * 
	 */
	public void checkIn() {
		DateTime currentTime = new DateTime();
		int currentRealDayofWeek = currentTime.getDayOfWeek();
		Day currentStampInDay = currentWeek.days.get(currentRealDayofWeek);
		currentStampInDay.checkInTime.add(currentTime);
		System.out.println("CheckIn: " + currentTime);
	}

	
	/**
	 * CheckOut on the current day and time
	 * TODO skall göras snyggare, inte prio just nu
	 * 
	 */
	public void checkOut() {
		DateTime currentTime = new DateTime();
		int currentRealDayofWeek = currentTime.getDayOfWeek();
		Day currentStampInDay = currentWeek.days.get(currentRealDayofWeek);
		currentStampInDay.checkOutTime.add(currentTime);
		System.out.println("CheckOut: " + currentTime);
	}
	
	/**
	 * Populates the schedule with empty days, ranging from 0 to 6
	 * Skall senare byggas ut till en metod där admin skapar specifierade veckor, kan nu fungera i testsyfte
	 * 
	 */

	
	//Dessa två metoder skapar för närvarande ett helt år med schedule objekt fyllda med tomma dagar.
	private void populateWeek(Week week) {
		
		for(int i = 0; i < 7; i++) {
			Day day = new Day();
			day.dayNr = i;
			week.days.add(day);
		}
	}
	
	private void populateYear() {
		
		for(int i = 0; i < 52; i++) {
			Week newWeek = new Week();
			newWeek.weekNr = i;
			populateWeek(newWeek);
			userSchedule.weekList.add(newWeek);
		}
	}
	
	/** 
	 * Returnerar en sorterad lista med in och utcheckningar för dagen, avslutat med "in" eller "out".
	 * Kan användas i loggningssyften eller andra syften än själva stapelbyggandet.
	 * 
	 * 
	public ArrayList<String> scheduleToDays (int dayOfWeek) {
		ArrayList<String> timeList = new ArrayList<String>();
		Day d = currentSchedule.days.get(dayOfWeek);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("kk:mm");
		for (DateTime stamp : d.checkInTime) {
			
			timeList.add(fmt.print(stamp) + " in");
		}
		
		for (DateTime stamp : d.checkOutTime) {
			timeList.add(fmt.print(stamp) + " out");
		}
		
		Collections.sort(timeList);
		System.out.println(timeList);

		return timeList;

	}
	**/
	
	
	/**
	 * Translates real time stamps to minutes for easy building of schemastaplarna.
	 * @param dayofWeek
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> scheduleToDays (int dayofWeek) {
		ArrayList<ArrayList<Integer>> totalList = new ArrayList<ArrayList<Integer>>(2);
		ArrayList<Integer> checkInList = new ArrayList<Integer>(currentWeek.days.get(dayofWeek).checkInTime.size());
		ArrayList<Integer> checkOutList = new ArrayList<Integer>(currentWeek.days.get(dayofWeek).checkInTime.size());
		for (DateTime stamp : currentWeek.days.get(dayofWeek).checkInTime) {
			checkInList.add(stamp.getMinuteOfDay());
		}
		for (DateTime stamp : currentWeek.days.get(dayofWeek).checkOutTime) {
			checkOutList.add(stamp.getMinuteOfDay());
		}
		totalList.add(checkInList);
		totalList.add(checkOutList);
		System.out.println(totalList);
		return totalList;
			
		
	}
	
}
