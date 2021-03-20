package ua.kharkiv.catalog.given;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple checker that helps to find differences between two several objects
 * based on the contents of the fields of these objects.
 * <p>It is a utility class that checks the contents of fields using the reflection mechanism.
 */
public class ObjectFieldsDiffChecker {
    /**
     * Returns {@code List} contains data from fields of the first
     * object which do not match with data from the second object.
     *
     * @param firstObject  an instance of first object to find differences
     * @param secondObject an instance of the second objects to find differences
     * @return the {@code List}
     * object that contains data from fields of first object
     * if differences are existed, otherwise the list will be empty
     * @throws IllegalAccessException if operation getting filed
     *                                by the method {@link Field#get} is failed
     * @see {@link Field#get}
     * @see {@link Field#setAccessible(boolean)}
     */
    public static List<Object> checkDiffFieldsOfSameObject(Object firstObject, Object secondObject)
            throws IllegalAccessException {
        if (firstObject == null && secondObject == null) {
            return Collections.emptyList();
        }

        if (firstObject == null) {
            return getAllFiledName(secondObject);
        }

        if (secondObject == null) {
            return getAllFiledName(firstObject);
        }

        Field[] fields = firstObject.getClass().getDeclaredFields();
        List<Object> fieldList = new ArrayList<>(fields.length);

        if (firstObject.getClass().equals(secondObject.getClass())) {
            for (Field field : fields) {

                field.setAccessible(true);
                Object source = field.get(firstObject);
                Object target = field.get(secondObject);

                if (!Objects.equals(source, target)) {
                    fieldList.add(field.getName());
                }
            }
        }

        return fieldList;
    }

    /**
     * Obtains list with all field names from an object.
     *
     * @param obj a target object
     * @return the list that contains all fields name of object.
     */
    private static List<Object> getAllFiledName(Object obj) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        List<Object> list = new ArrayList<>(declaredFields.length);

        for (Field field : declaredFields) {
            list.add(field.getName());
        }

        return list;
    }
}
