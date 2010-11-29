package validation;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class DaoValidator implements MethodBeforeAdvice {
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		Validatable obj = (Validatable) args[0];
		obj.validate();
	}
}

