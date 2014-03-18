/**
 * Extended JButton with extra fields to display time directly in the button.
 * 
 * @author David Stromner
 * @version 2014-03-12
 */

package swing;

import javax.swing.JButton;

import model.schedule.TimeSlot;

public class TimeJButton extends JButton{
	private static final long serialVersionUID = -6719797369107636050L;
	private TimeSlot timeSlot;
	
	public TimeJButton(TimeSlot timeSlot){
		super();
		this.timeSlot = timeSlot;
	}
	
	public TimeJButton(String s, TimeSlot timeSlot){
		super(s);
		this.timeSlot = timeSlot;
	}
	
	public TimeSlot getTimeSlot(){
		return timeSlot;
	}

}
