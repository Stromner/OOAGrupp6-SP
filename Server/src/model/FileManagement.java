/**
 * Read a txt file from hdd and return it to whomever needs it,
 * Save a txt file to hdd.
 * Return password for specific persNr
 * 
 * @author Henrik Johansson
 * @version 2013-02-13
 */


package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class FileManagement {
	private Date date;
	private DateFormat dateFormat;
	private final String USERSLIST_FILEPATH = "users.bin";		//Chose here filename or path.
	private String LOG_FILEPATH = "log.txt";
	private boolean file;

	public FileManagement(){
		
	}
	
	/**
	 * This is a "first time method" it should only be used if there are no admin users in users.bin. 
	 * This will make a complete new file and all old users will be removed!
	 * 
	 * @param fileName
	 */
	public void writeUsersFile(String fileName){				
		try{
			Users users = new Users();
			users.add("Admin", "Admin" , "Admin");
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName) );
			out.writeObject(users);
			out.close();
		}catch(IOException e){
			System.out.println("Could not write file");
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves users to binfile with a specific name as parameter.
	 * 
	 * @param fileName
	 * @param users
	 */
	public void writeUsersFile(String fileName, Users users){				
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName) );
			out.writeObject(users);
			out.close();
		}catch(IOException e){
			System.out.println("Could not write file");
		}
	}
	
	/**
	 * Writes users to default binfile choosen as constant.
	 * 
	 * @param users
	 */
	public void writeUsersFile(Users users){				
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERSLIST_FILEPATH) );
			out.writeObject(users);
			out.close();
		}catch(IOException e){
			System.out.println("Could not write file");
		}
	}
	
	/**
	 * Recieve the Users.
	 * 
	 * @return Users if there are som, null ow
	 */
	public Users getUsersList(){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(USERSLIST_FILEPATH));
			Users users = (Users)in.readObject();
			in.close();
			return users;
		}catch(Exception e){
			return null;
		}
	}
	/*
	 * @author Olof Spetz
	 * Logs every login/logout made against the server in log.txt
	 * @param status
	 * @param ip
	 */
	
	public void addToLog(String status, String ip)
	{
		
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();
		
		try
		{	
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILEPATH,true)));
			out.println(status +"\t"+ ip + "\t" + dateFormat.format(date) );
			out.close();
		}
		catch(IOException e)
		{
			System.out.println("Could not write to " + LOG_FILEPATH);
		}
	}
	/*
	 * @Author Olof Spetz
	 * Reads from log and displays in server GUI TextArea
	 * @param textArea
	 */
	public void readFromLog(JTextArea textArea)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(LOG_FILEPATH));
			textArea.read(reader, null);
			reader.close();
		}
		catch(IOException e)
		{
			System.out.println("Could not read from " + LOG_FILEPATH);
			File file = new File(LOG_FILEPATH);
		} 

	}
	/*
	 * @Author Olof Spetz
	 *  Checks if log file exists, if not a new one is created.
	 */
	public void createLog()
	{
		File log = new File(LOG_FILEPATH);
		
		if(!log.exists())
		{
			try 
			{
				file = new File(LOG_FILEPATH).createNewFile();
			}
			catch(IOException e)
			{
				System.out.println("Could not create: " + LOG_FILEPATH);
			}
		}
	}
	
}
