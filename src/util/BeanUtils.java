package util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
/**
 * Spring BeanUtils facade
 * @author zhaoxu
 *
 */
public final class BeanUtils {
	public static void copyProperties(Object source, Object target, String[] ignoreProperties) {
		org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
	}
	public static void copyProperties(Object source, Object target) {
		org.springframework.beans.BeanUtils.copyProperties(source, target);
	}
	public static void copyTheseProperties(Object source, Object target, String[] properties) {
		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = org.springframework.beans.BeanUtils.getPropertyDescriptors(actualEditable);
		List<String> propertyList = Arrays.asList(properties);
		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null && propertyList.contains(targetPd.getName())) {
				PropertyDescriptor sourcePd = org.springframework.beans.BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						Method writeMethod = targetPd.getWriteMethod();
						if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
							writeMethod.setAccessible(true);
						}
						writeMethod.invoke(target, value);
					}
					catch (Throwable ex) {
					}
				}
			}
		}
	}
}
