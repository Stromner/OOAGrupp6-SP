/**
 * Each connecton to the server should initiate a new Communication object.
 *
 * @author Henrik Johansson
 * @version 2013-02-25
 */

package model;

import java.util.LinkedList;
import java.util.Observable;

// Absolutely nothing is observable but a shortcut around having to rework schedule
// TODO rework schedule
public class Communication extends Observable {

	private FileManagement fileMan;

	private User user;
	private Users users;
	private ClientHandler clientHandler;

	public Communication(ClientHandler clientHandler) {
		this.clientHandler = clientHandler;
		fileMan = new FileManagement();
	}

	/**
	 * Check what type of message that has been recieved. TODO fix case instead
	 * of if statements
	 */
	public void messageRecieved(LinkedList<Object> linkedMessage) {
		if (linkedMessage != null) {
			// this leads to slow server should be one of these in server and
			// not separate for each user
			users = fileMan.getUsersList();
			System.out.println("Message recieved in communication Server"
					+ linkedMessage.toString());
			String whatToDo = (String) linkedMessage.get(0);
			// Login has been recieved previous:
			// linkedMessage.get(0).equals("login")
			if (whatToDo.compareToIgnoreCase("Login") == 0) {
				loginRecieved(linkedMessage);
				fileMan.addToLog(whatToDo, clientHandler.getAddress());
			} else if (whatToDo.compareToIgnoreCase("Logout") == 0) {
				fileMan.addToLog(whatToDo, clientHandler.getAddress());
			} else if (whatToDo.compareToIgnoreCase("GetAllUsers") == 0) {
				LinkedList<String> linkedMessageReturn = new LinkedList<String>();
				linkedMessageReturn.add("GetAllUsers");
				String[] usersListStringArray = users.getAllPerNr().toString()
						.split(", ");

				for (int i = 0; i < usersListStringArray.length; i++) {
					// Removes [] that was recieved from LinkedList.toString
					usersListStringArray[i] = usersListStringArray[i].replace(
							"[", "");
					usersListStringArray[i] = usersListStringArray[i].replace(
							"]", "");
					linkedMessageReturn.add(usersListStringArray[i]);
				}

				clientHandler.send(linkedMessageReturn);

				// Sends LinkedList<(String)"GetUser", (User)user> back to
				// client
			} else if (whatToDo.compareToIgnoreCase("GetUser") == 0) {
				String persNr = (String) linkedMessage.get(1);
				user = users.getUser(persNr);
				sendGetUser();

			} else if (whatToDo.compareToIgnoreCase("RemoveUser") == 0
					|| whatToDo.compareToIgnoreCase("DeleteUser") == 0) {
				String persNr = (String) linkedMessage.get(1);
				users.remove(persNr);
				fileMan.writeUsersFile(users);

			} else if (whatToDo.compareToIgnoreCase("NewUser") == 0) {
				// users.add((String) linkedMessage.get(1), (String)
				// linkedMessage.get(2), (String) linkedMessage.get(3));
				users.add((User) linkedMessage.get(1));

				fileMan.writeUsersFile(users);

			} else if (whatToDo.compareToIgnoreCase("EditUser") == 0) { //
				User user = users.getUser((String) linkedMessage.get(1));
				user.setPassword((String) linkedMessage.get(2));
				user.setStatus((String) linkedMessage.get(3));
				fileMan.writeUsersFile(users);

			} else if (whatToDo.compareToIgnoreCase("ChangePassword") == 0) {

				users.changePass(user.getPerNr(), (String) linkedMessage.get(1));
				fileMan.writeUsersFile(users);

			} else if (whatToDo.compareToIgnoreCase("CheckIn") == 0) {
				user.getSchedule().checkIn();
				users.replace(user);
				fileMan.writeUsersFile(users);
				sendGetUser();
			} else if (whatToDo.compareToIgnoreCase("CheckOut") == 0) {
				user.getSchedule().checkOut();
				users.replace(user);
				fileMan.writeUsersFile(users);
				sendGetUser();
			} else if (whatToDo.compareToIgnoreCase("NewTimeSlot") == 0) {
				user.getSchedule().addPlannedTime((int) linkedMessage.get(1),
						(int) linkedMessage.get(2), (int) linkedMessage.get(3),
						(int) linkedMessage.get(4), (int) linkedMessage.get(5));
				users.replace(user);
				fileMan.writeUsersFile(users);
				sendGetUser();
			} else if (whatToDo.compareToIgnoreCase("CreateDefaultSchedule") == 0) {
				users.replace(user);
				fileMan.writeUsersFile(users);
			}

		}
	}
	
	private void sendGetUser(){
		LinkedList<Object> linkedMessageReturn = new LinkedList<Object>();
		linkedMessageReturn.add("GetUser");
		linkedMessageReturn.add(user);
		clientHandler.send(linkedMessageReturn);
	}

	/**
	 * Handles what to do when a login has been recieved.
	 * 
	 * @param iaddr
	 *            InetAddress
	 * @param message
	 *            The message to be sent
	 * @param comm
	 *            What communication object to use
	 */
	public void loginRecieved(LinkedList<Object> message) {
		if (message.get(0) instanceof String
				&& message.get(1) instanceof String
				&& message.get(2) instanceof String) {

			try { 
				// If you send empty password server will crash without catch
				String recievedPassword = (String) message.get(2);

				user = users.getUser((String) message.get(1)); // Get all info
																// from the
																// user.

				if (recievedPassword.equals(user.getPassword())) {

					// Now true is to be sent back
					String status = user.getStatus(); // Get what status the
														// user has
					LinkedList<String> loginStatus = new LinkedList<String>();
					loginStatus.add("login"); // Add login as first string in
												// the list so the client knows
												// what it is recieving
					loginStatus.add(status); // Adding the status as second
												// string in the list so the
												// client know what GUI to open.
					System.out.println("login" + status
							+ ": is sent back due to RIGHT password:");

					LinkedList<Object> listToSend = new LinkedList<Object>();
					listToSend.add("login");
					listToSend.add(status);
					if (status.equals("Employee")) {
						sendGetUser();
					}

					clientHandler.send(listToSend);

				} else {
					System.out
							.println("False is sent back due to WRONG password");

					LinkedList<String> listToSend = new LinkedList<String>();
					listToSend.add("login");
					listToSend.add("false");

					clientHandler.send(listToSend);

					clientHandler.closeSocket();

				}
			} catch (Exception e) {
				System.out
						.println("Did not recieve password, \nor if you are experimenting with the User class user.bin file might be outdated or corrupt.\nTry the reset input param for main.");

				LinkedList<String> listToSend = new LinkedList<String>();
				listToSend.add("login");
				listToSend.add("false");

				clientHandler.send(listToSend);

				clientHandler.closeSocket();

			}
		}
	}

}
