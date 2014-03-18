/**
 * Dialog for displaying information about a time slot.
 * 
 * @author David Stromner
 * @version 2014-02-24
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.schedule.TimeSlot;

public class TimeSlotDialog extends CustomDialog {
	private static final long serialVersionUID = -3660230252379856775L;
	TimeSlot timeSlot;
	
	public TimeSlotDialog(Object ... o) {
		super();
		this.timeSlot = (TimeSlot)o[0];
		setTitle("Time Slot Dialog");
	}

	/**
	 * Create all the components that is going to be used inside the dialog.
	 */
	@Override
	protected void create() {
		super.create();
		Container temp;
		
		// TimeLabel
		temp = new JLabel("Time: "+ timeSlot.getStartTime() + " ->" + timeSlot.getStopTime());
		components.put("timeLabel", temp);
		
		// DateLabel
		temp = new JLabel("Date");
		components.put("dateLabel", temp);
	}

	/**
	 * Place all the created components.
	 */
	@Override
	protected void build() {
		super.build();
		GridBagConstraints c;
		JPanel canvas = getCanvas();

		// TimeLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 10);
		canvas.add(components.get("timeLabel"), c);

		// DateLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 10);
		canvas.add(components.get("dateLabel"), c);
	}
}
