package service;

import model.Vehicle;
import exception.ScheduleException;

public interface ScheduleService {
	void addStartBiddingSchedule(Vehicle veh) throws ScheduleException;
	void addStopBiddingSchedule(Vehicle veh) throws ScheduleException;
	void removeAllSchedule(Vehicle veh) throws ScheduleException;
}

