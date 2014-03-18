/**
 * Dialog for creating a new time slot for a day in a given week.
 * 
 * @author David Stromner
 * @version 2014-02-28
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.joda.time.DateTime;

import swing.TimeTextPanel;
import controller.ActionHandler;

public class NewTimeSlotDialog extends StandardButtonsDialog {
	private static final long serialVersionUID = 6654368973245373300L;

	public NewTimeSlotDialog(Object ... o) {
		super();

		setTitle("New Time Slot");
	}

	/**
	 * Create all the components that is going to be used inside the dialog.
	 */
	@Override
	protected void create() {
		super.create();
		Container temp;

		// TimePanel
		temp = new JPanel();
		((JPanel) temp).setLayout(new GridBagLayout());
		components.put("timePanel", temp);

		// InfoLabel
		temp = new JLabel(
				"Create a new time slot for the given day, week and year");
		components.put("infoLabel", temp);

		// DayLabel
		temp = new JLabel("Day:");
		components.put("dayLabel", temp);

		// WeekLabel
		temp = new JLabel("Week:");
		components.put("weekLabel", temp);

		// YearLabel
		temp = new JLabel("Year:");
		components.put("yearLabel", temp);

		// StartLabel
		temp = new JLabel("Start:");
		components.put("startLabel", temp);

		// StopLabel
		temp = new JLabel("Stop:");
		components.put("stopLabel", temp);

		// DayComboBox
		String[] dayArr = { "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday", "Saturday", "Sunday" };
		temp = new JComboBox(dayArr);
		components.put("dayComboBox", temp);

		// WeekComboBox
		String[] weekArr = new String[52];
		for (int i = 0; i < 52; i++) {
			weekArr[i] = Integer.toString(i + 1);
		}
		temp = new JComboBox(weekArr);
		components.put("weekComboBox", temp);

		// YearComboBox
		DateTime dt = new DateTime();
		String[] yearArr = { "" + dt.getYear(), "" + (dt.getYear() + 1),
				"" + (dt.getYear() + 2) };
		temp = new JComboBox(yearArr);
		components.put("yearComboBox", temp);

		// StartText
		temp = new TimeTextPanel("00:00");
		components.put("startText", temp);

		// StopText
		temp = new TimeTextPanel("00:00");
		components.put("stopText", temp);

		// An OKButton needs to be created in a subclass to CustomDialog since
		// each OKButton is going to trigger different things.
		// OkButton
		temp = new JButton("Ok");
		((JButton) temp).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance()
						.newTimeSlotDialogOk(customDialog,
								components.get("yearComboBox"),
								components.get("weekComboBox"),
								((JComboBox<?>)components.get("dayComboBox")).getSelectedIndex(),
								components.get("startText"),
								components.get("stopText"));
			}
		});
		components.put("okButton", temp);

	}

	/**
	 * Place all the created components.
	 */
	@Override
	protected void build() {
		super.build();
		GridBagConstraints c;
		JPanel canvas = getCanvas();
		JPanel timePanel = (JPanel) components.get("timePanel");

		// InfoLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		c.gridwidth = 4;
		c.insets = new Insets(0, 0, 30, 0);
		canvas.add(components.get("infoLabel"), c);

		// DayLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		canvas.add(components.get("dayLabel"), c);

		// WeekLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 0, 2, 10);
		canvas.add(components.get("weekLabel"), c);

		// YearLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		canvas.add(components.get("yearLabel"), c);

		// DayComboBox
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("dayComboBox"), c);

		// WeekComboBox
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("weekComboBox"), c);

		// YearComboBox
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("yearComboBox"), c);

		// TimePanel
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.gridheight = 3;
		canvas.add(timePanel, c);

		// StartLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 5, 10);
		timePanel.add(components.get("startLabel"), c);

		// StopLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(5, 0, 0, 10);
		timePanel.add(components.get("stopLabel"), c);

		// StartText
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 5, 0);
		timePanel.add(components.get("startText"), c);

		// StopText
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 0, 0, 0);
		timePanel.add(components.get("stopText"), c);
	}
}
