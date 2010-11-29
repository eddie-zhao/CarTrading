package validation;

import java.io.IOException;
import org.apache.struts2.ServletActionContext;
import org.springframework.aop.ThrowsAdvice;
import exception.InvalidAccessGroupException;

public class ExceptionAdvice implements ThrowsAdvice {
	/* method */
	public void afterThrowing(Exception ex) {
		try {
			if (ex instanceof InvalidAccessGroupException) {
//				InvalidAccessGroupException iae = (InvalidAccessGroupException) ex;
				//TODO crack logging
			}
			ServletActionContext.getResponse().sendRedirect("user-login.action");
		} catch (IOException e) {
		}
	}
}
