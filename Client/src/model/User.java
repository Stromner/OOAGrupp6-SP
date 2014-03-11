/**
 * Has one specific user"s" all combined information
 * 
 * @author Henrik Johansson
 * @version 2014-02-26
 */

package model;

import java.io.Serializable;

import model.schedule.Schedule;

public class User implements Serializable {
	private static final long serialVersionUID = -6000874821084879926L;

	private String perNr, password, status, name;
	private Schedule schedule;

	/**
	 * @param perNr
	 * @param password
	 * @param status
	 */
	public User(String perNr, String password, String status) {
		this.perNr = perNr;
		this.password = password;
		this.status = status;
		this.schedule = new Schedule();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerNr() {
		return perNr;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Schedule getSchedule() {
		return schedule;
	}

}
