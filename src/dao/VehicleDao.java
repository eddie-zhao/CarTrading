package dao;

import java.util.List;
import model.Vehicle;
import model.VehicleStatus;

public interface VehicleDao {
	Vehicle get(int uid);
	void save(Vehicle vehicle);
	void update(Vehicle vehicle);
	List<Vehicle> getVehicles(int pageSize, int pageNum);
	List<Vehicle> getVehicles(VehicleStatus status, int pageSize, int pageNum);
}
