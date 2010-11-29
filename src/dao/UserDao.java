package dao;

import java.util.List;
import model.Group;
import model.User;

public interface UserDao {
	User get(int id);
	User get(String loginName);
	List<User> getUsers(Group group, String userName, int pageSize, int pageNum);
	int save(User user);
	void update(User user);
}
