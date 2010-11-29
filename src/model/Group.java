package model;

public enum Group {
	NONE(0),//All invalid groups
	ALL(-1),//for list all users
	ADMIN(1),
	BUYER(2),
	CUSTOMER(3),
	;
	
	/* method */
	Group(int id) {
		this.id = id;
	}
	public int toInt() {
		return id;
	}
	public static Group of(int id) {
		for (Group g : Group.values()) {
			if (g.id == id) {
				return g;
			}
		}
		return NONE;
	}
	public static Group of(String groupName) {
		Group g = Group.valueOf(groupName.toUpperCase());
		if (g == null) {
			g = NONE;
		}
		return g;
	}
	public static Group of(Object object) {
		return of(String.valueOf(object));
	}
	
	/* field */
	private int id;
	public int getId() {
		return id;
	}
}
