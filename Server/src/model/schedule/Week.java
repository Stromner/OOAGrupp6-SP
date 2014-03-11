/**
 * Contains 7 days and keep track on logged time from the user and planned time for the user.
 * 
 * @author David Stromner
 * @version 2014-03-07
 */

package model.schedule;

import java.io.Serializable;
import java.util.LinkedList;

import org.joda.time.DateTime;

public class Week implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5081642018795597039L;
	private final int NUMBEROFDAYS = 7;
	private LinkedList<Day> days;
	private Day currentDay;
	private int year, week;

	/**
	 * Create a new week with the given params.
	 * 
	 * @param year
	 *            given year for this week.
	 * @param week
	 *            given week during a year.
	 */
	public Week(int year, int week) {
		this.year = year;
		this.week = week;
		days = new LinkedList<Day>();

		// Mon first ... Sun last
		for (int i = 0; i < NUMBEROFDAYS; i++) {
			days.addLast(new Day());
		}
		currentDay = days.get(new DateTime().getDayOfWeek() - 1);
	}

	public int getYear() {
		return year;
	}

	public int getWeek() {
		return week;
	}

	/**
	 * @return a list of all the days starting with Monday(0).
	 */
	public LinkedList<Day> getDays() {
		return days;
	}

	/**
	 * If the last time slot's stop time for the current day is not equal to
	 * -1(i.e no stop time have been given yet), then ignore checking in.
	 * Otherwise creates new time slot at the end of the linked list.
	 */
	public void checkIn() {
		if (currentDay.getLoggedTime().size() == 0 || currentDay.getLoggedTime().getLast().getStopTime() != -1) {
			DateTime temp = new DateTime();
			currentDay.getLoggedTime().addLast(new TimeSlot(Integer.parseInt(""+temp.getHourOfDay()+temp.getMinuteOfHour())));
		}
	}

	/**
	 * If the last time slot's stop time for the current day is equal to -1(i.e
	 * no stop time have been given yet), then check out. Otherwise no check in
	 * has been made and check out will ignore.
	 */
	public void checkOut() {
		if (currentDay.getLoggedTime().size() > 0 && currentDay.getLoggedTime().getLast().getStopTime() == -1) {
			DateTime temp = new DateTime();
			currentDay.getLoggedTime().getLast().setStopTime(Integer.parseInt(""+temp.getHourOfDay()+temp.getMinuteOfHour()));
		}
	}
}
