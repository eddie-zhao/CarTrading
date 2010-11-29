package model;

public enum VehicleStatus {
	NONE(0),//all invalid status
	ALL(-1),//used for list vehicles
	INIT(1),
	SCHEDULED(2),
	BIDDING(3),
	FINISHED(4),
	;

	public static VehicleStatus of(int intValue) {
		for (VehicleStatus vs : VehicleStatus.values()) {
			if (vs.value == intValue) {
				return vs;
			}
		}
		return NONE;
	}
	public static VehicleStatus of(String statusName) {
		VehicleStatus vs = VehicleStatus.valueOf(statusName.toUpperCase());
		if (vs == null) {
			vs = VehicleStatus.NONE;
		}
		return vs;
	}
	VehicleStatus(int value) {
		this.value = value;
	}
	private int value;
	public int toInt() {
		return value;
	}
}

