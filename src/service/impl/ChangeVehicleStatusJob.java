package service.impl;

import model.Vehicle;
import model.VehicleStatus;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoaderListener;
import service.VehicleService;
import util.Constants;

public class ChangeVehicleStatusJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		VehicleService vehicleServcie = ContextLoaderListener.getCurrentWebApplicationContext().getBean(VehicleService.class);
		JobDataMap jobData = context.getJobDetail().getJobDataMap();
		int vehId = jobData.getInt(Constants.VEHICLE_ID);
		VehicleStatus statusOld = (VehicleStatus) jobData.get(Constants.VEHICLE_STATUS_OLD);
		VehicleStatus statusNew = (VehicleStatus) jobData.get(Constants.VEHICLE_STATUS_NEW);
		Vehicle veh = vehicleServcie.getVehicle(vehId);
		if (statusOld == veh.getStatus() && statusNew != veh.getStatus()) {
			veh.setStatus(statusNew);
			vehicleServcie.update(veh);
		}
	}
}
