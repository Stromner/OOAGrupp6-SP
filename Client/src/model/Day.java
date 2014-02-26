/**
 * Write a description of class Day here.
 * 
 * @author David Stromner
 * @version 2013-02-06
 */

/*
 Schema uppbyggnad

 Object för varje dag, håller reda på nödvändig information.

 Object lagras i nångon form av lista eller tree set.

 */


package model;


import java.io.Serializable;
import java.util.ArrayList;

import org.joda.time.*;

public class Day implements Serializable {
	
	private static final long serialVersionUID = -7092684252961129250L;

	
	//Variabler här har inga modifiers (private etc.) så att de kan anropas från samma paket utan att behöva skicka
	//getters och setters-metoder i varje day objekt till servern.
	
	//Dagens nummer i veckan, börjar på 0, kanske inte behövs då de lagras i ordning i Schema-objektets dayList
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