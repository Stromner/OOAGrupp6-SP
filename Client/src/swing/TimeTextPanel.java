/**
 * Custom panel consisting of two integer fields.
 * 
 * @author David Stromner
 * @version 2014-03-03
 */

package swing;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TimeTextPanel extends JPanel {
	private static final long serialVersionUID = -7774067380432105202L;
	private TimeField hour, minute;

	public TimeTextPanel(String text) {
		super();
		
		String[] temp = text.split(":");
		
		hour = new TimeField(temp[0], 24);
		minute = new TimeField(temp[1], 60);
		
		add(hour);
		add(minute);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}

	public TimeField getHour() {
		return hour;
	}

	public TimeField getMinute(){
		return minute;
	}
	
	public String getTime(){
		return hour.getText()+minute.getText();
	}
}
