package L07ReflectionAndAnnotation.P02GettersAndSetters;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Method[] methods = Reflection.class.getDeclaredMethods();

        Method[] getters = Arrays.stream(methods).filter(method ->
                method.getName().startsWith("get") && method.getParameterCount() == 0)
                .sorted(Comparator.comparing(Method::getName)).toArray(Method[]::new);

        Method[] setters = Arrays.stream(methods).filter(method ->
                method.getName().startsWith("set") && method.getParameterCount() == 1)
                .sorted(Comparator.comparing(Method::getName)).toArray(Method[]::new);

        Arrays.stream(getters).forEach(method ->
                System.out.printf("%s will return class %s%n",
                        method.getName(), method.getReturnType().getName()));

        Arrays.stream(setters).forEach(method ->
                System.out.printf("%s and will set field of class %s%n",
                        method.getName(), method.getParameterTypes()[0].getName()));
    }
}
