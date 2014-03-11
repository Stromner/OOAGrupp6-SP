package view.schedule;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.schedule.Day;
import model.schedule.Schedule;
import model.schedule.TimeSlot;
import net.miginfocom.swing.MigLayout;
import controller.ActionHandler;

public class JDay extends JLayeredPane {
	private static final long serialVersionUID = -7329575071810998439L;
	private Schedule schedule;
	private int day;

	public JDay(Schedule schedule, int day) {
		this.day = day;
		this.schedule = schedule;

		setLayout(new MigLayout("fill, wrap 1"));
		init();
	}

	private void init() {
		if (schedule != null) {
			Day tempDay = schedule.getCurrentWeek().getDays().get(day);
			int layer;
			
			// Add all planned panels
			layer = 0;
			for (TimeSlot timeSlot : tempDay.getPlannedTime()) {
				JButton button = new JButton();
				add(button, ""+calculatePercentage(timeSlot.getStartTime(),timeSlot.getStopTime()) + ", " + calculatePercentage(timeSlot.getStartTime()));
				setLayer(button, layer);
				button.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						ActionHandler.getInstance().getTimeSlotDialog(timeSlot);
					}
				});
			}
			
			// Add all worked panels
			layer = 1;
			for( TimeSlot timeSlot: tempDay.getLoggedTime()){
				JPanel panel = new JPanel();
				panel.setOpaque(true);
				panel.setBackground(new Color(0,255,0,130));
				add(panel, ""+calculatePercentage(timeSlot.getStartTime(),timeSlot.getStopTime()) + ", " + calculatePercentage(timeSlot.getStartTime()));
				setLayer(panel, layer);
			}
		}

	}

	/**
	 * Convert the time params from hours and minutes into percent span between
	 * the start and stop node.
	 * 
	 * @param startTime
	 * @param stopTime
	 * @return converted params.
	 */
	private String calculatePercentage(int startTime, int stopTime) {
		// First two values: hours, the other two: minutes
		float time = stopTime - startTime;
		float totalTime = 24 * 60;
		int hours = (int) (time / 100);
		int minutes = (int) (time - hours * 100);
		float percentage = (float) (hours * 60 + minutes) / totalTime;
		percentage *= 100;
		return "h " + (int) percentage + "%, w 100%";
	}

	/**
	 * Convert time from hours and minutes into percent.
	 * 
	 * @param time
	 * @return converted params.
	 */
	private String calculatePercentage(int time) {
		// First two values: hours, the other two: minutes
		float totalTime = 24 * 60;
		int hours = (int) (time / 100);
		int minutes = (int) (time - hours * 100);
		float percentage = (float) (hours * 60 + minutes) / totalTime;
		percentage *= 100;
		return "pos 0% " +  percentage + "%";
	}
}
