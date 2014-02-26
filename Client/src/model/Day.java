/**
 * Write a description of class Day here.
 * 
 * @author David Stromner
 * @version 2013-02-06
 */

/*
 Schema uppbyggnad

 Object f�r varje dag, h�ller reda p� n�dv�ndig information.

 Object lagras i n�ngon form av lista eller tree set.

 */


package model;


import java.io.Serializable;
import java.util.ArrayList;

import org.joda.time.*;

public class Day implements Serializable {
	
	private static final long serialVersionUID = -7092684252961129250L;

	
	//Variabler h�r har inga modifiers (private etc.) s� att de kan anropas fr�n samma paket utan att beh�va skicka
	//getters och setters-metoder i varje day objekt till servern.
	
	//Dagens nummer i veckan, b�rjar p� 0, kanske inte beh�vs d� de lagras i ordning i Schema-objektets dayList
	public int dayNr;
	ArrayList<DateTime> checkInTime;
	ArrayList<DateTime> checkOutTime;
	
	public Day() {
		checkInTime = new ArrayList<DateTime>();
		checkOutTime = new ArrayList<DateTime>();
	}
	

	DateTime scheduledInTime;
	DateTime scheduledOutTime;
	
}