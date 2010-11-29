package validation;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;
import util.Constants;
import com.opensymphony.xwork2.ActionContext;
import exception.InvalidLoginStatusException;

public class LoginValidator implements MethodBeforeAdvice {
	/* method */
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		ActionContext context = ActionContext.getContext();
		if (!context.getSession().containsKey(Constants.USER_ID)) {
			throw new InvalidLoginStatusException();
		}
	}
}
