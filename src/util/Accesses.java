package util;

public enum Accesses {
	ACC_A(1, "acc_a"),
	ACC_B(2, "acc_b"), ;
	Accesses(int id, String label) {
		this.id = id;
		this.label = label;
	}
	public int getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	private int id;
	private String label;

	private static void generateSql() {
		for (Accesses acc : Accesses.values()) {
			System.out.printf("insert Access(id, name) values(%d, '%s')%n",
					acc.getId(), acc.getLabel());
		}
	}

	public static void main(String[] args) {
		generateSql();
	}
}
