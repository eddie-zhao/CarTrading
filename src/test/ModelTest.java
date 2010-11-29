package test;

import model.Group;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Execute sql/test_data.sql before test case.
 * 
 * @author zhaoxu
 * 
 */
public class ModelTest {
	private static SessionFactory sessionFactory;
	private Session session;
	@BeforeClass
	public static void setupClass() {
		sessionFactory = new Configuration().configure(
				ModelTest.class.getResource("hibernate.cfg.xml"))
				.buildSessionFactory();
	}
	@Before
	public void startUp() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
	}
	@After
	public void tearDown() {
		session.getTransaction().rollback();
	}

	@Test
	public void testUser() {
//		User user = (User) session.get(User.class, 2);
//		Vehicle veh = user.getVehicle();
//		System.out.println(veh.getVehicleStatus());
		User user = User.newInstance();
		user.setLoginName("test")
			.setName("test")
			.setGroup(Group.ADMIN)
			;
		session.save(user);
	}
}
