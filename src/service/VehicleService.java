package service;

import java.util.List;
import model.Vehicle;
import model.VehicleStatus;

public interface VehicleService {
	Vehicle getVehicle(int id);
	Vehicle getVehicle(String encryptedId);
	/**
	 * Before calling this, make sure the user was proper saved.
	 * 
	 * @param user
	 * @param vehicle
	 */
	void save(Vehicle vehicle);
	void update(Vehicle vehicle);
	List<Vehicle> getVehicles(VehicleStatus status, int page);
}
