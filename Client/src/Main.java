/**
 * Start the program by calling the singelton method in Workflow
 * 
 * @version 2013-02-16
 */

import org.joda.time.DateTime;

import controller.Workflow;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Workflow.getInstance();
	}

}
