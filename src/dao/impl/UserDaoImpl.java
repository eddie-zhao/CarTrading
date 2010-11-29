package dao.impl;

import java.sql.SQLException;
import java.util.List;
import model.Group;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import util.CollectionUtils;
import dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public User get(int id) {
		return getHibernateTemplate().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(final Group group, final String userName, final int pageSize, final int pageNum) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<User>>() {
			@Override
			public List<User> doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder("from User where 1=1");
				List<Object> paramList = CollectionUtils.newArrayList();
				if (!group.equals(Group.ALL)) {
					sql.append(" and gid=?");
					paramList.add(group.toInt());
				}
				if (!userName.isEmpty()) {
					sql.append(" and (loginName like ? or name like ?)");
					paramList.add(userName);
					paramList.add(userName);
				}
				sql.append(" order by id desc");
				Query qry = session.createQuery(sql.toString());
				for (int i = 0; i < paramList.size(); i++) {
					qry.setParameter(i, paramList.get(i));
				}
				qry.setFirstResult(pageSize * pageNum)
					.setMaxResults(pageSize * pageNum + pageSize);
				return qry.list();
			}
		});
	}

	@Override
	public int save(User user) {
		getHibernateTemplate().save(user);
		return user.getId();
	}

	@Override
	public User get(final String loginName) {
		return getHibernateTemplate().execute(new HibernateCallback<User>() {
			@Override
			public User doInHibernate(Session session) throws HibernateException, SQLException {
				Object obj = session.createQuery("from User where loginName=?").setParameter(0, loginName).uniqueResult();
				if (obj == null) {
					return null;
				} else {
					return (User) obj;
				}
			}
		});
	}

	@Override
	public void update(User user) {
		getHibernateTemplate().update(user);
	}

}
