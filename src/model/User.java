package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import util.BeanUtils;
import util.CollectionUtils;
import util.SecurityUtils;
import validation.Validatable;
import exception.InvalidPropertyException;

public class User implements Validatable {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
	private static final String[] PROPERTIES_NOT_ASSIGNABLE = {"id", "user"};
	/* method */
	protected User() {
	}
	public static User newInstance() {
		User user = new User();
		return user;
	}
	/**
	 * @throws InvalidPropertyException
	 */
	@Override
	public boolean validate() {
		if (loginName == null || loginName.trim().isEmpty()) {
			throw new InvalidPropertyException("loginName");
		}
		for (Vehicle veh : vehicles) {
			veh.validate();
		}
		return true;
	}
	public Vehicle getVehicle() {
		if (vehicles.isEmpty()) {
			Vehicle veh = new Vehicle();
			vehicles.add(veh);
			veh.setUser(this);
			return veh;
		} else {
			return vehicles.iterator().next();
		}
	}
	public void setVehicle(Vehicle veh) {
		if (veh == null) {
			vehicles.clear();
			return;
		}
		if (vehicles.isEmpty()) {
			vehicles.add(veh);
			veh.setUser(this);
		} else if (!vehicles.contains(veh)) {
			BeanUtils.copyProperties(veh, vehicles.iterator().next(), PROPERTIES_NOT_ASSIGNABLE);
		}
	}
	public String getRegTimeString() {
		return DATE_FORMAT.format(regTime);
	}
	
	/* field */
	private int id;
	private String loginName;
	private String name;
	private String password;
	private Date regTime;
	private Group group;
	private Set<Vehicle> vehicles = CollectionUtils.newHashSet(2);
	private String encryptedId;
	public String getEncryptedId() {
		if (encryptedId == null) {
			encryptedId = SecurityUtils.volatileEncrypt(id);
		}
		return encryptedId;
	}
	public void setEncryptedId(String id) {
		encryptedId = id;
	}
	public int getId() {
		return id;
	}
	public User setId(int id) {
		this.id = id;
		return this;
	}
	public String getLoginName() {
		return loginName;
	}
	public User setLoginName(String loginName) {
		this.loginName = loginName;
		return this;
	}
	public String getName() {
		return name;
	}
	public User setName(String name) {
		this.name = name;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	public Date getRegTime() {
		return regTime;
	}
	public User setRegTime(Date regTime) {
		this.regTime = regTime;
		return this;
	}
	public User setGroup(Group group) {
		this.group = group;
		return this;
	}
	public Group getGroup() {
		return group;
	}
	protected void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	protected Set<Vehicle> getVehicles() {
		return vehicles;
	}
}
