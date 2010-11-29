package validation;

import java.lang.reflect.Method;
import model.Group;
import org.springframework.aop.MethodBeforeAdvice;
import util.Constants;
import com.opensymphony.xwork2.ActionContext;
import exception.InvalidAccessGroupException;

public class AdminValidator implements MethodBeforeAdvice {
	/* method */
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		if (!Group.ADMIN.equals(ActionContext.getContext().getSession().get(Constants.GROUP))) {
			String userId = String.valueOf(ActionContext.getContext().getSession().get(Constants.USER_ID));
			throw new InvalidAccessGroupException(userId, target, method);
		}
	}
}
