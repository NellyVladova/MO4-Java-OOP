package L07ReflectionAndAnnotation.P03HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Field[] fields = Reflection.class.getDeclaredFields();

        Arrays.stream(fields).filter(field -> !Modifier.isPrivate(field.getModifiers()))
                .sorted((Comparator.comparing(Field::getName)))
                .forEach(field -> System.out.printf("%s must be private!\n", field.getName()));

        Method[] getters = Reflection.class.getDeclaredMethods();

        Arrays.stream(getters).filter(method -> method.getName().startsWith("get") && !Modifier.isPublic(method.getModifiers()))
                .sorted((Comparator.comparing(Method::getName)))
                .forEach(method -> System.out.printf("%s have to be public!\n", method.getName()));

        Method[] setters = Reflection.class.getDeclaredMethods();

        Arrays.stream(setters).filter(method -> method.getName().startsWith("set") && !Modifier.isPrivate(method.getModifiers()))
                .sorted((Comparator.comparing(Method::getName)))
                .forEach(method -> System.out.printf("%s have to be private!\n", method.getName()));

    }
}
