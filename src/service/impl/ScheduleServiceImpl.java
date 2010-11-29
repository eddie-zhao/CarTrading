package service.impl;

import java.util.Date;
import model.Vehicle;
import model.VehicleStatus;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import service.ScheduleService;
import util.Constants;
import exception.ScheduleException;

public class ScheduleServiceImpl implements ScheduleService {
	private static final String VEHICLE_STATUS = "vehicle-status";
	private static final String START_BIDDING = "start-bidding";
	private static final String STOP_BIDDING = "stop-bidding";
	/* method */
	@Override
	public void addStartBiddingSchedule(Vehicle veh) throws ScheduleException {
		changeVehicleStatus(START_BIDDING, veh, VehicleStatus.SCHEDULED, VehicleStatus.BIDDING, veh.getBiddingStartOn());
	}
	@Override
	public void addStopBiddingSchedule(Vehicle veh) throws ScheduleException {
		changeVehicleStatus(STOP_BIDDING, veh, VehicleStatus.BIDDING, VehicleStatus.FINISHED, veh.getBiddingStopOn());
	}
	@Override
	public void removeAllSchedule(Vehicle veh) throws ScheduleException {
		try {
			scheduler.deleteJob(String.format("%s-%d", START_BIDDING, veh.getId()), VEHICLE_STATUS);
			scheduler.deleteJob(String.format("%s-%d", STOP_BIDDING, veh.getId()), VEHICLE_STATUS);
		} catch (SchedulerException e) {
			throw new ScheduleException(e);
		}
	}
	private void changeVehicleStatus(String jobNamePrefix, Vehicle veh, VehicleStatus fromStatus, VehicleStatus toStatus, Date when)
			throws ScheduleException {
		if (fromStatus != toStatus) {
			String jobName = String.format("%s-%d", jobNamePrefix, veh.getId());
			Trigger trigger = new SimpleTrigger(jobName, VEHICLE_STATUS, when);
			JobDetail job = new JobDetail(jobName, VEHICLE_STATUS, ChangeVehicleStatusJob.class);
			JobDataMap jobData = job.getJobDataMap();
			jobData.put(Constants.VEHICLE_ID, veh.getId());
			jobData.put(Constants.VEHICLE_STATUS_OLD, fromStatus);
			jobData.put(Constants.VEHICLE_STATUS_NEW, toStatus);
			try {
				scheduler.scheduleJob(job, trigger);
			} catch (SchedulerException e) {
				throw new ScheduleException(e);
			}
		}
	}
	
	/* field */
	private Scheduler scheduler;
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
}
