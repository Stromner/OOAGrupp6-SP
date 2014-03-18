/**
 * Contains all weeks for a user from the first day the user started working to the most futuristic planned week.
 * 
 * @author David Stromner
 * @version 2014-03-06
 */

package model.schedule;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import model.Communication;
import model.User;

import org.joda.time.DateTime;

public class Schedule extends Observable implements Observer, Serializable {
	private static final long serialVersionUID = 4436229904004252295L;
	// YearWeek(201445, 20145, 199511)
	private LinkedHashMap<String, Week> weekList;
	private User user;
	private Week currentWeek;

	public Schedule() {
		// No user or new user
		weekList = new LinkedHashMap<String, Week>();
		currentWeek = new Week(new DateTime().getYear(), new DateTime().getWeekOfWeekyear());
		weekList.put("" + currentWeek.getYear() + currentWeek.getWeek(), currentWeek);
		setChanged();
		notifyObservers();
	}

	public void update(Observable o, Object arg) {
		if (o instanceof Communication) {
			LinkedList<?> argsList = (LinkedList<?>) arg;
			// A user with an existing schedule came, set us to that schedule.
			if (((String) argsList.get(0)).compareToIgnoreCase("GetUser") == 0) {
				user = (User) argsList.get(1);
				weekList = user.getSchedule().weekList;
				
				DateTime time = new DateTime();
				// The current week doesn't exist, create empty weeks from last
				// known week up to this week.
				if (weekList.get("" + time.getYear() + time.getWeekOfWeekyear()) == null) {
					createWeeks();
				}
				this.currentWeek = weekList.get("" + time.getYear() + time.getWeekOfWeekyear());
				setChanged();
				notifyObservers();
			}
		}
	}

	public Week getCurrentWeek() {
		return currentWeek;
	}

	public User getUser() {
		return user;
	}

	/**
	 * @return wanted week if it exist. null otherwise.
	 */
	public Week getNextWeek() {
		setChanged();
		
		// currentWeek = 51, thus +1 is 0.
		if (currentWeek.getWeek() + 1 > 52 && weekList.get("" + (currentWeek.getYear() + 1) + 0) != null) {
			notifyObservers();
			return currentWeek = weekList.get("" + (currentWeek.getYear() + 1) + 0);
		} else if(weekList.get("" + currentWeek.getYear() + (currentWeek.getWeek() + 1)) != null){
			notifyObservers();
			return currentWeek = weekList.get("" + currentWeek.getYear() + (currentWeek.getWeek() + 1));
		}
		
		notifyObservers();
		return currentWeek;
	}

	/**
	 * @return wanted week if it exist. null otherwise.
	 */
	public Week getPreviousWeek() {
		setChanged();
		
		// currentWeek = 0, thus -1 is 51.
		if (currentWeek.getWeek() - 1 < 0 && weekList.get("" + (currentWeek.getYear() - 1) + 51) != null) {
			notifyObservers();
			return currentWeek = weekList.get("" + (currentWeek.getYear() - 1) + 51);
		} else if(weekList.get("" + currentWeek.getYear() + (currentWeek.getWeek() - 1)) != null){
			notifyObservers();
			return currentWeek = weekList.get("" + currentWeek.getYear() + (currentWeek.getWeek() - 1));
		}
		
		notifyObservers();
		return currentWeek;
	}

	/**
	 * Add a planned time slot for the given day.
	 * 
	 * @param year
	 *            the year for the time slot
	 * @param week
	 *            the week for the time slot
	 * @param day
	 *            the day for the time slot.
	 * @param start
	 *            time for the time slot.
	 * @param stop
	 *            time for the time slot.
	 */
	public void addPlannedTime(int year, int week, int day, int start, int stop) {
		// Week didn't exist, create up to and including the week.
		if (weekList.get("" + year + week) == null) {
			createWeeks(year, week);
		}
		// Add the time slot to the given week.
		weekList.get("" + year + week).getDays().get(day)
				.addPlannedTime(start, stop);
	}

	/**
	 * Create week from the last known week of the last know year up to params
	 * 
	 * @param year ending year.
	 * @param week given week.
	 */
	private void createWeeks(int year, int week) {
		Iterator<Week> it = weekList.values().iterator();
		Week tempWeek = currentWeek; // Assign a week which we know exists.
		// Get last week
		while (it.hasNext()) {
			tempWeek = it.next();
		}

		int lastYear = tempWeek.getYear(), lastWeek = tempWeek.getWeek();
		// Loop through and create all years and weeks.
		while (lastYear <= year && lastWeek < week) {
			if (lastWeek + 1 > 52) {
				lastWeek = 0;
				lastYear++;
			} else {
				lastWeek++;
			}
			weekList.put("" + lastYear + lastWeek, new Week(lastYear, lastWeek));
		}
	}
	
	/**
	 * Create week from the last known week of the last know year up to current week of current year.
	 */
	private void createWeeks() {
		int year = currentWeek.getYear(), week = currentWeek.getWeek();
		
		Iterator<Week> it = weekList.values().iterator();
		Week tempWeek = currentWeek; // Assign a week which we know exists.
		// Get last week
		while (it.hasNext()) {
			tempWeek = it.next();
		}

		int lastYear = tempWeek.getYear(), lastWeek = tempWeek.getWeek();
		// Loop through and create all years and weeks.
		while (lastYear <= year && lastWeek < week) {
			if (lastWeek + 1 > 52) {
				lastWeek = 0;
				lastYear++;
			} else {
				lastWeek++;
			}
			weekList.put("" + lastYear + lastWeek, new Week(lastYear, lastWeek));
		}
	}

	public void checkIn() {
		currentWeek.checkIn();
	}

	public void checkOut() {
		currentWeek.checkOut();
	}

	// TODO log out user automatically at the end of the day and re-log him in
	// at the start of the next day.
}
