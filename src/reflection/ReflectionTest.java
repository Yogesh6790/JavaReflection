package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ReflectionTest {

	public static void main(String[] args) {
		Class reflectClass = ReflectionClass.class;
		String className = reflectClass.getName();
		
		int classModifier = reflectClass.getModifiers();
		
		System.out.println(Modifier.isPublic(classModifier));
		
		Class[] interfaces = reflectClass.getInterfaces();
		Method[] methods = reflectClass.getMethods();
		
		Arrays.asList(interfaces).forEach(System.out::println);
		Arrays.asList(methods).forEach(x -> {
			if (x.getName().startsWith("get")) {
				System.out.println("Getter method");
			} else if (x.getName().startsWith("set")) {
				System.out.println("Setter method");
			}
			System.out.println("Return Type: " + x.getReturnType());
			Class[] parameterTypes = x.getParameterTypes();
			Arrays.asList(parameterTypes).forEach(y->System.out.println("parameter"+y.getName()));
			System.out.println();
		});
		
		Class superClass = reflectClass.getSuperclass();
		System.out.println("Super Class"+superClass.getName());
		
		Constructor constructor = null;
		try {
			constructor = reflectClass.getConstructor(String.class, int.class);
		} catch (NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}
		try {
			ReflectionClass instance = (ReflectionClass) constructor
					.newInstance("Random String", 44);
			System.out.println(instance);
			instance.setVar1("String 2");
			System.out.println(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Field privateStringName = null;
		ReflectionClass reflectionClass = new ReflectionClass("yyy",99);
		try {
			// Dynamically access any field even when they are encapsulated
			privateStringName = ReflectionClass.class.getDeclaredField("var1");
			System.out.println(privateStringName);
			privateStringName.setAccessible(true);
			String valueOfPrivateField = (String) privateStringName
					.get(reflectionClass);
			System.out.println(valueOfPrivateField);
			
			//Dynamically invoke any methods(private/public/protected)
			String methodName = "privateMethod";
			Method privateMethod = ReflectionClass.class.getDeclaredMethod(
					methodName, null);
			privateMethod.setAccessible(true);
			privateMethod.invoke(reflectionClass, null);
			
			Class[] methodParamType = new Class[]{String.class};
			Object[] params = new Object[]{"**Value***"};
			
			privateMethod = ReflectionClass.class.getDeclaredMethod(
					"privateMethodWithParams", methodParamType);
			privateMethod.setAccessible(true);
			privateMethod.invoke(reflectionClass, params);
			
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException
				| NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
