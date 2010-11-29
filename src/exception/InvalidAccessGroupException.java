package exception;

import java.lang.reflect.Method;

public class InvalidAccessGroupException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/* method */
	public InvalidAccessGroupException(String userId, Object target, Method method) {
		this.userId = userId;
		this.target = target;
		this.method = method;
	}
	@Override
	public String toString() {
		return String.format("Invalid user access. [userId=%s]", userId);
	}

	/* field */
	private String userId;
	private Object target;
	private Method method;
	public String getUserId() {
		return userId;
	}
	public Object getTarget() {
		return target;
	}
	public Method getMethod() {
		return method;
	}
}
