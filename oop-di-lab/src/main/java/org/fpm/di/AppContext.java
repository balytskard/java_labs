package org.fpm.di;

import org.fpm.di.annotations.*;
import org.fpm.di.annotations.Configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AppContext implements Container {
    Map<Class<?>, Object> objectRegistryMap = new HashMap<>();

    public AppContext(Class<?> clazz) {
        initializeContext(clazz);
    }

    private <T> void injectAnnotatedFields(T object, Field[] declaredFields) throws Exception {
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                Object innerObject = objectRegistryMap.get(type);

                field.set(object, innerObject);
                injectAnnotatedFields(innerObject, type.getDeclaredFields());
            }
        }
    }

    private void initializeContext(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Configuration.class)) {
            throw new RuntimeException("Please provide a valid configuration file!");
        } else {
            ComponentScan componentScan = clazz.getAnnotation(ComponentScan.class);
            String packageValue = componentScan.value();
            Set<Class<?>> classes = findClasses(packageValue);

            for (Class<?> loadingClass: classes) {
                try {
                    if (loadingClass.isAnnotationPresent(SimpleComponent.class)) {
                        Constructor<?> constructor = loadingClass.getDeclaredConstructor();
                        Object newInstance = constructor.newInstance();
                        objectRegistryMap.put(loadingClass, newInstance);
                    }
                    if (loadingClass.isAnnotationPresent(Singleton.class)) {
                        if (objectRegistryMap.containsValue(loadingClass)) {
                            continue;
                        }
                        Constructor<?> [] constructor = loadingClass.getConstructors();
                        Object newInstance = constructor[0].newInstance();
                        objectRegistryMap.put(loadingClass, newInstance);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Set<Class<?>> findClasses(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        assert stream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getComponent(Class<T> clazz) throws Exception {
        T object = (T) objectRegistryMap.get(clazz);

        Field[] declaredFields = clazz.getDeclaredFields();
        injectAnnotatedFields(object, declaredFields);

        return object;
    }
}
