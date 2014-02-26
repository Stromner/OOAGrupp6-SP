package model;

import org.joda.time.DateTime;

public class Test {
	
	public Test() {
		ScheduleHandler scheduleHandler = new ScheduleHandler(0);
		DateTime dt1 = new DateTime(2014,02,26,8,04,04);
		DateTime dt2 = new DateTime(2014,02,26,16,00,00);
		DateTime dt3 = new DateTime(2014,02,27,10,30,00);
		DateTime dt4 = new DateTime(2014,02,26,18,00,00);
		
		scheduleHandler.currentSchedule.days.get(0).checkInTime.add(dt1);
		scheduleHandler.currentSchedule.days.get(0).checkOutTime.add(dt2);
		scheduleHandler.currentSchedule.days.get(1).checkInTime.add(dt3);
		scheduleHandler.currentSchedule.days.get(1).checkOutTime.add(dt4);
		scheduleHandler.scheduleToDays(0);
		
	}

}
