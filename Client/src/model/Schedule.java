/**
 * Represents one week with seven days
 * 
 * @author Simon Planhage
 * @version 2014-02-25
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Serializable {

	private static final long serialVersionUID = -4841663954805333902L;
	
	int personNummer;
	
	ArrayList<Week> weekList = new ArrayList<Week>();

}
