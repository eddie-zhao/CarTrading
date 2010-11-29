package service.impl;

import java.util.Date;
import java.util.List;
import model.Group;
import model.User;
import service.UserService;
import util.Constants;
import util.SecurityUtils;
import dao.UserDao;
import exception.UserExistedException;

public class UserServiceImpl implements UserService {
	@Override
	public User getUser(int id) {
		return userDao.get(id);
	}
	
	@Override
	public User getUser(String encryptedId) {
		try {
			int id = Integer.valueOf(SecurityUtils.volatileDecrypt(encryptedId));
			return userDao.get(id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> getUsers(Group userGroup, String userName, int page) {
		if (userGroup == null || userGroup.equals(Group.NONE)) {
			userGroup = Group.ALL;
		}
		if (userName == null || userName.isEmpty()) {
			userName = "";
		} else {
			userName = "%" + userName.trim() + "%";
		}
		return userDao.getUsers(userGroup, userName, Constants.PAGE_SIZE_USER_LIST, page);
	}

	private UserDao userDao;
	public void setUserDao(UserDao val) {
		userDao = val;
	}

	@Override
	public void save(User user) throws UserExistedException {
		if (userDao.get(user.getLoginName()) != null) {
			throw new UserExistedException();
		}
		user.setPassword(SecurityUtils.encryptPassword(user.getLoginName(), user.getPassword()));
		user.setRegTime(new Date());
		userDao.save(user);
	}

	@Override
	public User getUser(String loginName, String password) {
		User user = userDao.get(loginName);
		if ((user != null) &&
				user.getPassword().equals(SecurityUtils.encryptPassword(loginName, password))) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}
}
