package me.jacobruby.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static Field getField(Class clazz, String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);

            return field;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void setFieldValue(Object object, String fieldName, Object value) {
        Field field = getField(object.getClass(), fieldName);

        if (field == null)
            throw new NullPointerException("No field with the name '" + fieldName + "' was found in class '" + object.getClass().getName() + "'");

        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getField(object.getClass(), fieldName);

        if (field == null)
            throw new NullPointerException("No field with the name '" + fieldName + "' was found in class '" + object.getClass().getName() + "'");

        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
