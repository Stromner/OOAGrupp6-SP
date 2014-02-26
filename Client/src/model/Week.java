/**
 * Represents one week with seven days
 * 
 * @author Simon Planhage
 * @version 2014-02-25
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Week implements Serializable {


	private static final long serialVersionUID = -8613882887180242145L;


	//Vecka som schemat representerar
	int weekNr;
	
	//Lista med veckodagarna som hör till
	public ArrayList<Day> days = new ArrayList<Day>();

}