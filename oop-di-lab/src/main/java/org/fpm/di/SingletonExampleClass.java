package org.fpm.di;

import org.fpm.di.annotations.Singleton;

@Singleton
public final class SingletonExampleClass {
    private static SingletonExampleClass instance;

    public String value;

    public SingletonExampleClass() {
        this.value = "defaultStringValue";
    }


    public static SingletonExampleClass getInstance() {
        if (instance == null) {
            instance = new SingletonExampleClass();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Singleton Class value: " + value;
    }
}
