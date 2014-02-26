/**
 * Represents one week with seven days
 * 
 * @author Simon Planhage
 * @version 2014-02-25
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

import org.joda.time.*;

@SuppressWarnings("unused")
public class Schedule implements Serializable {

	private static final long serialVersionUID = -4841663954805333902L;

	//Vecka som schemat representerar
	int week;
	
	//Lista med veckodagarna som hör till
	public ArrayList<Day> days = new ArrayList<Day>();
	
	public Schedule() {}

}
