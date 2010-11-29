package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import model.Group;
import model.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class UserServiceTest {
	private ApplicationContext context;
	private UserService userService;

	@BeforeClass
	public static void setupClass() throws Exception {
		File config = new File("WebContent/WEB-INF/classes/WEB-INF/config.txt");
		if (!config.exists()) {
			if (!config.getParentFile().exists()) {
				config.getParentFile().mkdirs();
			}
			config.createNewFile();
			FileWriter out = new FileWriter(config);
			out.write("db.ip=192.168.0.65\n");
			out.write("db.port=1433\n");
			out.write("db.name=zhaoxu\n");
			out.write("db.user=sa\n");
			out.write("db.password=f,d,r5138\n");
			out.close();
		}
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		File config = new File("WebContent/WEB-INF/classes/WEB-INF/config.txt");
		config.delete();
		config.getParentFile().delete();
	}

	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = context.getBean(UserService.class);
		assertNotNull(userService);
	}

	@Test
	public void test() {
		List<User> users = userService.getUsers(Group.ALL, null, 0);
		assertTrue(users.size() > 0);
	}
}
