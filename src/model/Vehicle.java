package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import util.SecurityUtils;
import validation.Validatable;

public class Vehicle implements Validatable {
	private static final SimpleDateFormat FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/* Constructor */
	protected Vehicle() {
	}
	public static Vehicle newInstance() {
		return new Vehicle();
	}
	
	/* method */
	@Override
	public boolean validate() {
		return true;
	}
	public String getEncryptedId() {
		if (encryptedId == null) {
			encryptedId = SecurityUtils.volatileEncrypt(id);
		}
		return encryptedId;
	}
	public void setEncryptedId(String val) {
		encryptedId = val;
	}
	
	/* field */
	private int id;
	private VehicleStatus status;
	private String brand;
	private String licensePlate;
	private User user;
	private String encryptedId;
	private Date biddingStartOn;
	private Date biddingStopOn;
	private String biddingStartOnString;
	private String biddingStopOnString;
	public Date getBiddingStartOn() {
		return biddingStartOn;
	}
	public void setBiddingStartOn(Date biddingStartOn) {
		this.biddingStartOn = biddingStartOn;
		biddingStartOnString = null;
	}
	public Date getBiddingStopOn() {
		return biddingStopOn;
	}
	public void setBiddingStopOn(Date biddingStopOn) {
		this.biddingStopOn = biddingStopOn;
		biddingStopOnString = null;
	}
	public Vehicle setId(int uid) {
		this.id = uid;
		return this;
	}
	public int getId() {
		return id;
	}
	public Vehicle setBrand(String brand) {
		this.brand = brand;
		return this;
	}
	public String getBrand() {
		return brand;
	}
	public Vehicle setLicensePlate(String val) {
		licensePlate = val;
		return this;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public Vehicle setUser(User user) {
		this.user = user;
		return this;
	}
	public User getUser() {
		return user;
	}
	public Vehicle setStatus(VehicleStatus vehicleStatus) {
		this.status = vehicleStatus;
		return this;
	}
	public VehicleStatus getStatus() {
		return status;
	}
	public void setBiddingStartOnString(String val) {
		biddingStartOnString = val;
	}
	public String getBiddingStartOnString() {
		if (biddingStartOnString == null) {
			biddingStartOnString = biddingStartOn == null ? "" : FORMAT_DATETIME.format(biddingStartOn);
		}
		return biddingStartOnString;
	}
	public void setBiddingStopOnString(String val) {
		biddingStopOnString = val;
	}
	public String getBiddingStopOnString() {
		if (biddingStopOnString == null) {
			biddingStopOnString = biddingStopOn == null ? "" : FORMAT_DATETIME.format(biddingStopOn);
		}
		return biddingStopOnString;
	}
}
