package action;

import java.util.List;
import java.util.Map;
import model.Group;
import model.User;
import service.UserService;
import util.BeanUtils;
import util.CollectionUtils;
import util.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import exception.UserExistedException;

public class UserAction extends ActionSupport implements CommonAction {
	private static final long serialVersionUID = 1L;
	private static final String[] USER_PROPERTIES_UPDATABLE = {"name","group"};

	/* method */
	@Override
	public String setRedirectAction(String toAction) {
		redirectAction = toAction;
		return REDIRECT_ACTION;
	}
	@Override
	public String getRedirectAction() {
		return redirectAction;
	}
	@Override
	public String setCustomPage(String page) {
		customPage = page;
		return CUSTOM_PAGE;
	}
	@Override
	public String getCustomPage() {
		return customPage;
	}
	/**
	 * User logout.
	 * Remove session variants.
	 */
	public String logout() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(Constants.USER_ID);
		session.remove(Constants.USER_NAME);
		session.remove(Constants.GROUP);
		return SUCCESS;
	}
	/**
	 * User login.
	 * Store user informations in session.
	 */
	public String login() {
		if (user == null) {
			return ERROR;
		}
		user = userService.getUser(user.getLoginName(), user.getPassword());
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (user != null) {
			session.put(Constants.USER_ID, user.getId());
			session.put(Constants.USER_NAME, user.getName());
			session.put(Constants.GROUP, user.getGroup());
			return setCustomPage(String.format("/WEB-INF/content/user/%sHome.jsp", user.getGroup().toString().toLowerCase()));
		} else {
			session.remove(Constants.USER_ID);
			session.remove(Constants.USER_NAME);
			session.remove(Constants.GROUP);
			return ERROR;
		}
	}
	/**
	 * List all users.
	 */
	public String listUsersAction() {
		if (user == null) {
			user = User.newInstance();
			user.setGroup(Group.ALL);
			user.setName("");
		}
		users = userService.getUsers(user.getGroup(), user.getName(), 0);
		return SUCCESS;
	}
	/**
	 * Get user informations by encrypted userId
	 */
	public String editUserAction() {
		user = userService.getUser(id);
		return SUCCESS;
	}
	/**
	 * Update user informations.
	 */
	public String updateUserAction() {
		User tempUser = userService.getUser(user.getEncryptedId());
		BeanUtils.copyTheseProperties(user, tempUser, USER_PROPERTIES_UPDATABLE);
		userService.update(tempUser);
		return setRedirectAction("user-listUsers");
	}
	/**
	 * Create new user
	 * @return
	 */
	public String addUserAction() {
		user = User.newInstance();
		return SUCCESS;
	}
	/**
	 * Save new user
	 */
	public String saveUserAction() {
		try {
			userService.save(user);
			return setRedirectAction("user-listUsers");
		} catch (UserExistedException e) {
			return ERROR;
		}
	}
	
	/* field */
	private UserService userService;
	private String redirectAction;
	private String customPage;
	private User user;
	private List<User> users;
	private String id;
	public void setId(String val) {
		id = val;
	}
	public String getId() {
		return id;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public List<User> getUsers() {
		if (users == null) {
			users = CollectionUtils.newArrayList(0);
		}
		return users;
	}
}
