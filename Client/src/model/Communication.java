/**
 * Set up a connection to the server with IP and port number from the config file.
 * Contains a looped recive method which checks if any new data is available.
 * Contains a send method which takes in objects, put them into a linked list and
 * send it to the server.
 * 
 * @author Erik Hermansson, David Stromner
 * @version 2013-02-25
 */

package model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;

import controller.Workflow;

public class Communication extends Observable {

	private Socket socket;
	private BufferedInputStream checkIn;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String ip;
	private int portNumber;
	private Boolean socketOpen;

	public Communication() {
		socketOpen = false;
	}

	/**
	 * Set up a new socket with the ip and port number from the config.txt file.
	 * Creates a new thread that constantly checks for information in.
	 */
	private void connect() {
		String s = FileManagement.getInstance().readLine("config.txt");
		String[] sArr = s.split(":");
		ip = sArr[0];
		portNumber = Integer.parseInt(sArr[1]);

		if (socket == null) {
			try {
				socket = new Socket(InetAddress.getByName(ip), portNumber);
				socket.setSoTimeout(10000);

				out = new ObjectOutputStream(socket.getOutputStream());
				socketOpen = true;

				new Thread() {
					public void run() {
						recieve();
					}
				}.start();
			} catch (IOException e) {
				Workflow.getInstance().getWindow().setErrorMessage("No server available!");
				disconnect();
			}
		}
	}

	/**
	 * Close the connected socket, reset it states so a new socket can be
	 * created from the same variables.
	 */
	public void disconnect() {
		try {
			// Possible null pointers exception can happen if these are not
			// checked
			out.close();
			out = null;
			in.close();
			in = null;
			socket.close();
			socket = null;
			socketOpen = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the input stream if no earlier input stream exists and the socket is
	 * connected.
	 */
	private void openInputStream() {
		if (in == null) {
			try {
				checkIn = new BufferedInputStream(socket.getInputStream());
				in = new ObjectInputStream(checkIn);
			} catch (IOException e) {
				disconnect();
			}
		}
	}

	/**
	 * Send a message to the server with all the given params. The first object
	 * is always the type of message.
	 * 
	 * @param args
	 *            additional arguments to be sent with the message.
	 */
	public void send(Object... args) {
		connect();

		LinkedList<Object> argsList = new LinkedList<Object>();
		for (Object o : args) {
			argsList.add(o);
		}

		try {
			out.writeObject(argsList);
			out.flush();
		} catch (Exception e) {
			disconnect();
		}
	}

	/**
	 * Constantly loops and check if any information inwards is available.
	 */
	private void recieve() {
		openInputStream();
		while (socketOpen) {
			try {
				// Naughty but works.
				while (checkIn.available() != 0) {
					// Check if there is any data waiting
					setChanged();
					notifyObservers(in.readObject());
				}
			} catch (Exception e) {
				disconnect();
			}
		}
	}
}
