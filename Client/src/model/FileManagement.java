/**
 * Handles file management for reading and saving schedule objects to file
 * 
 * @author Simon Planhage
 * @version 2014-02-20
 */

package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManagement {

	private static FileManagement fm;

	private FileManagement() {

	}

	/**
	 * Singleton method.
	 * 
	 * @return The single instance of the object.
	 */
	public static FileManagement getInstance() {
		if (fm == null)
			fm = new FileManagement();
		return fm;
	}

	/**
	 * Reads one line and returns it as String
	 * 
	 * @param file
	 *            Name of the file to be read.
	 * @return First line of file as String
	 */
	public String readLine(String file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			line = br.readLine();
			br.close();

			return line;

			// IOException
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param s
	 * @param args
	 */
	public void writeStrings(String file, String... args) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(file));
			for (String o : args) {
				out.write(o);
				out.write(":");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Saves the current list of schedules to a file named personNummer.ser
	 * Some debug information remains
	 * @param personNummer 
	 */
	public void saveSchedules(int personNummer) {
		
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try {
		        fout = new FileOutputStream(personNummer + ".ser", true);
		        oos = new ObjectOutputStream(fout);
		        oos.writeObject(ScheduleHandler.scheduleList);
		        System.out.println("Schedule has been saved");
		} catch (Exception e) {
		        e.printStackTrace();
		} finally {
				try {
					oos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	
	/**
	 * Loads the file with saved Schedule objects
	 * Some debug information remains
	 * @param personNummer
	 */
	@SuppressWarnings("unchecked")
	public static void loadSchedules(int personNummer) {
		ObjectInputStream objInStream = null;
		FileInputStream streamIn = null;
		 try {
		        streamIn = new FileInputStream(0 + ".ser");
		        objInStream = new ObjectInputStream(streamIn);
				ArrayList<Schedule> readCase = (ArrayList<Schedule>) objInStream.readObject();
		        ScheduleHandler.scheduleList.addAll(readCase);
		        System.out.println("Schedule has been loaded");


		    } catch (Exception e) {

		        e.printStackTrace();
		 } finally {
		        if(objInStream != null){
		            try {
						objInStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		         } 
		 }
	}
}
