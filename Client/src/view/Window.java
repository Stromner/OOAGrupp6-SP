/**
 * Write a description of class Window here.
 * 
 * @author David Stromner
 * @version 2013-02-06
 */

package view;

import java.util.HashMap;

import javax.swing.JFrame;

public class Window {
	private HashMap<String,GUI> interfaceList;
	private JFrame frame;
	
	public Window(){
		interfaceList = new HashMap<String,GUI>();
		frame = new JFrame();
		// Init all the views
		initViews();
	}
	
	private void initViews(){
		interfaceList.put("Login", new LoginGUI());
		interfaceList.put("Admin", new AdminGUI());
		interfaceList.put("Employee", new EmployeeGUI());	
	}
	
	public Boolean SetView(String key){
		return true;
	}
}
