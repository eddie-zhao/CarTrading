package service.impl;

import java.util.List;
import model.Vehicle;
import model.VehicleStatus;
import service.VehicleService;
import util.Constants;
import util.SecurityUtils;
import dao.VehicleDao;

public class VehicleServiceImpl implements VehicleService {
	/* method */
	@Override
	public Vehicle getVehicle(int id) {
		Vehicle vehicle = vehicleDao.get(id);
		return vehicle;
	}
	@Override
	public Vehicle getVehicle(String encryptedId) {
		try {
			int id = Integer.valueOf(SecurityUtils.volatileDecrypt(encryptedId));
			return getVehicle(id);
		} catch (Exception e) {
			return null;
		}
	}
	@Override
	public void save(Vehicle vehicle) {
		vehicleDao.save(vehicle);
	}
	@Override
	public List<Vehicle> getVehicles(VehicleStatus status, int page) {
		if (status == null || status == VehicleStatus.ALL) {
			return vehicleDao.getVehicles(Constants.PAGE_SIZE_VEHILE_LIST, page);
		}
		return vehicleDao.getVehicles(status, Constants.PAGE_SIZE_VEHILE_LIST, page);
	}
	@Override
	public void update(Vehicle vehicle) {
		vehicleDao.update(vehicle);
	}
	
	/* field */
	private VehicleDao vehicleDao;
	public void setVehicleDao(VehicleDao val) {
		vehicleDao = val;
	}
}
