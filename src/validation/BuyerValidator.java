package validation;

import java.lang.reflect.Method;
import model.Group;
import org.springframework.aop.MethodBeforeAdvice;
import util.Constants;
import com.opensymphony.xwork2.ActionContext;
import exception.InvalidAccessGroupException;

public class BuyerValidator implements MethodBeforeAdvice {
	/* method */
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		Group group = Group.of(ActionContext.getContext().getSession().get(Constants.GROUP));
		if (!(Group.BUYER.equals(group) || Group.ADMIN.equals(group))) {
			String userId = String.valueOf(ActionContext.getContext().getSession().get(Constants.USER_ID));
			throw new InvalidAccessGroupException(userId, target, method);
		}
	}
}
