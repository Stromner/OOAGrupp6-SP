/**
 * Contains all the logged and planned time for this day.
 * 
 * @author David Stromner
 * @version 2014-03-07
 */

package model.schedule;

import java.io.Serializable;
import java.util.LinkedList;

public class Day implements Serializable{
	private static final long serialVersionUID = -6952680822314185328L;
	private LinkedList<TimeSlot> loggedTime;
	private LinkedList<TimeSlot> plannedTime;

	public Day() {
		loggedTime = new LinkedList<TimeSlot>();
		plannedTime = new LinkedList<TimeSlot>();
	}

	public LinkedList<TimeSlot> getLoggedTime() {
		return loggedTime;
	}

	public LinkedList<TimeSlot> getPlannedTime() {
		return plannedTime;
	}
	
	/**
	 * Add a planned time slot for the current day.
	 * 
	 * @param start
	 *            time for the time slot.
	 * @param stop
	 *            time for the time slot.
	 */
	public void addPlannedTime(int start, int stop) {
		plannedTime.addLast(new TimeSlot(start, stop));
	}
}
