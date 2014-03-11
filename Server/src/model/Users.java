/**
 * Has a HashMap with all users, and gives the ability to edit specific fields
 * on users.
 * 
 * @author Henrik Johansson
 * @version 2014-02-25
 */

package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class Users implements Serializable {
	private static final long serialVersionUID = -72807553467774375L;

	private static Users instance;

	private HashMap<String, User> usersList;

	public Users() {
		usersList = new HashMap<String, User>();
	}

	public void add(String perNrIn, String passwordIn, String statusIn) {
		if (!usersList.containsKey(perNrIn)) {
			usersList.put(perNrIn, new User(perNrIn, passwordIn, statusIn));
		}
	}

	public void add(User user) {
		if (!usersList.containsKey(user.getPerNr())) {
			usersList.put(user.getPerNr(), user);
		}
	}

	public void remove(String perNrIn) {
		if (usersList.containsKey(perNrIn)) {
			usersList.remove(perNrIn);
		}
	}

	public void changePass(String perNrIn, String passwordIn) {
		usersList.get(perNrIn).setPassword(passwordIn);
	}

	public void changeStatus(String perNrIn, String statusIn) {
		usersList.get(perNrIn).setStatus(statusIn);
	}

	public User getUser(String perNrIn) {
		return usersList.get(perNrIn);
	}

	public LinkedList<String> getAllPerNr() {
		LinkedList<String> listOfPersNr = new LinkedList<String>(); // Creates a
																	// new
																	// linkedlist
																	// and adds
																	// all keys
																	// from
																	// usersList
																	// to it.
		listOfPersNr.addAll(usersList.keySet()); // And then returns the list.

		return listOfPersNr;
	}

	public void replace(User user) {

		usersList.put(user.getPerNr(), user);

	}

}
