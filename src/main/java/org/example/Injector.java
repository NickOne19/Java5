package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * The type Injector.
 */
public class Injector {
    private Properties properties;

    /**
     * Instantiates a new Injector.
     *
     * @param filePath the file path
     */
    public Injector(String filePath) {
        this.properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (is != null) {
                properties.load(is);
            } else {
                throw new IOException("Properties file not found: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }
    }

    /**
     * Inject t.
     *
     * @param <T>    the type parameter
     * @param object the object
     * @return the t
     * @throws Exception the exception
     */
    public <T> T inject(T object) throws Exception {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String className = properties.getProperty(fieldType.getName());
                if (className != null) {
                    Class<?> implClass = Class.forName(className);
                    Object implInstance = implClass.getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(object, implInstance);
                } else {
                    throw new RuntimeException("Implementation not found for: " + fieldType.getName());
                }
            }
        }

        return object;
    }
}
