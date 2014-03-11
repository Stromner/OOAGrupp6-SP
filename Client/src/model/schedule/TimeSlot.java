/**
 * Contains a start and stop time with appropriate methods.
 * 
 * @author David Stromner
 * @version 2014-03-06
 *
 */

package model.schedule;

import java.io.Serializable;

public class TimeSlot implements Serializable{
	private static final long serialVersionUID = 8475961171290843015L;
	private int start;
	private int stop;
	
	public TimeSlot(int start, int stop){
		this.start = start;
		this.stop = stop;
	}
	
	/**
	 * Start time is equal to -1(no time given) 
	 */
	public TimeSlot(int start){
		this.start = start;
		stop = -1;
	}
	
	/**
	 * Start and stop time is equal to -1(no time given) 
	 */
	public TimeSlot(){
		start = -1;
		stop = -1;
	}
	
	public int getStartTime(){
		return start;
	}
	
	public int getStopTime(){
		return stop;
	}
	
	public void setStartTime(int start){
		this.start = start;
	}
	
	public void setStopTime(int stop){
		this.stop = stop;
	}
}
