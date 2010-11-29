package dao.impl;

import java.sql.SQLException;
import java.util.List;
import model.Vehicle;
import model.VehicleStatus;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import dao.VehicleDao;

public class VehicleDaoImpl extends HibernateDaoSupport implements VehicleDao {
	@Override
	public Vehicle get(int uid) {
		return getHibernateTemplate().get(Vehicle.class, uid);
	}
	@Override
	public void save(Vehicle vehicle) {
		getHibernateTemplate().saveOrUpdate(vehicle);
	}
	@Override
	public void update(Vehicle vehicle) {
		getHibernateTemplate().update(vehicle);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> getVehicles(final VehicleStatus status, final int pageSize, final int pageNum) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Vehicle>>() {
			@Override
			public List<Vehicle> doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery("from Vehicle where status=? order by id desc")
					.setParameter(0, status)
					.setFirstResult(pageNum)
					.setMaxResults(pageSize)
					.list();
			}
		});
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> getVehicles(final int pageSize, final int pageNum) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Vehicle>>() {
			@Override
			public List<Vehicle> doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery("from Vehicle order by id desc")
					.setFirstResult(pageNum)
					.setMaxResults(pageSize)
					.list();
			}
		});
	}
}
