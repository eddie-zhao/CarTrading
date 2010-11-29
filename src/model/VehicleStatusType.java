package model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

public class VehicleStatusType implements UserType {
	private static final int[] sqlTypes = new int[] {java.sql.Types.INTEGER};

	@Override
	public int[] sqlTypes() {
		return sqlTypes;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class returnedClass() {
		return VehicleStatus.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == null) {
			return y == null;
		} else {
			return x.equals(y);
		}
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		if (x == null) {
			return 0;
		} else {
			return x.hashCode();
		}
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		return VehicleStatus.of(rs.getInt(names[0]));
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			value = VehicleStatus.INIT;
		}
		st.setInt(index, ((VehicleStatus) value).toInt());
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}
}

