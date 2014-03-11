/**
 * @author David Stromner
 * @version 2014-03-06
 */

package view.schedule;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.schedule.Schedule;
import controller.ActionHandler;
import controller.Workflow;

public class JSchedule extends JPanel implements Observer {
	private static final long serialVersionUID = -1770872985394426334L;
	private final int NUMBEROFDAYS = 7;
	private Schedule schedule;
	protected HashMap<String, Container> components;

	public JSchedule() {
		components = new HashMap<String, Container>();
		this.setLayout(new BorderLayout());

		init();
		build();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Schedule) {
			schedule = (Schedule) o;
			removeAll();
			components.clear();

			init();
			build();

			repaint();
			revalidate();
		}
	}

	/**
	 * Create all the components unique for schedule.
	 */
	private void init() {
		Container temp;

		// LeftToolbarPanel
		temp = new JPanel();
		((JPanel) temp).setLayout(new GridLayout(0, 1));
		for (int i = 0; i < 24; i++) {
			JLabel label = new JLabel();
			label.setVerticalAlignment(SwingConstants.TOP);
			if (i < 10) {
				label.setText("0" + Integer.toString(i));
			} else {
				label.setText(Integer.toString(i));
			}
			((JPanel) temp).add(label);
		}
		components.put("leftToolbarPanel", temp);

		// TopToolbarPanel
		temp = new JPanel();
		((JPanel) temp).setLayout(new GridBagLayout());
		components.put("topToolbarPanel", temp);

		// WeekPanel
		temp = new JPanel();
		((JPanel) temp).setLayout(new GridLayout());
		components.put("weekPanel", temp);
		
		// WeekLabel
		temp = new JLabel("Week: ");
		components.put("weekLabel", temp);
		
		// PreviousWeekButton
		temp = new JButton("Previous week");
		((JButton) temp).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ActionHandler.getInstance().getPreviousWeek(components.get("weekLabel"));
			}
		});
		components.put("previousWeekButton", temp);

		// NextWeekButton
		temp = new JButton("Next week");
		((JButton) temp).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ActionHandler.getInstance().getNextWeek(components.get("weekLabel"));
			}
		});
		components.put("nextWeekButton", temp);
		
		// All JDays
		for (int i = 0; i < NUMBEROFDAYS; i++) {
			temp = new JDay(schedule, i);
			components.put("day" + i, temp);
		}
		
	}

	private void build() {
		GridBagConstraints c;

		// LeftToolbarPanel
		this.add(components.get("leftToolbarPanel"), BorderLayout.WEST);

		// TopToolbarPanel
		this.add(components.get("topToolbarPanel"), BorderLayout.NORTH);
		
		// PreviousWeekButton
		c = new GridBagConstraints();
		c.gridx = 0;
		components.get("topToolbarPanel").add(components.get("previousWeekButton"), c);
		
		// WeekLabel
		c = new GridBagConstraints();
		c.weightx = 1;
		components.get("topToolbarPanel").add(components.get("weekLabel"), c);
		
		// NextWeekButton
		c = new GridBagConstraints();
		c.gridx = 2;
		components.get("topToolbarPanel").add(components.get("nextWeekButton"), c);

		// WeekPanel
		this.add(components.get("weekPanel"), BorderLayout.CENTER);

		// All JDays
		for (int i = 0; i < NUMBEROFDAYS; i++) {
			components.get("weekPanel").add(components.get("day" + i));
		}

	}
}
