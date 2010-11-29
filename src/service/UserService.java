package service;

import java.util.List;
import model.Group;
import model.User;
import exception.UserExistedException;

public interface UserService {
	User getUser(int id);
	User getUser(String encryptedId);
	User getUser(String loginName, String password);
	List<User> getUsers(Group userGroup, String userName, int page);
	void save(User user) throws UserExistedException;
	void update(User user);
}
