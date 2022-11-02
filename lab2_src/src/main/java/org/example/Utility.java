package org.example;

public class Utility {
    public static <F extends Number, S extends Number> S castNumberToGeneric(Class<F> classType, Number number) {
        if (classType.equals(Byte.class)) return (S) classType.cast(number.byteValue());
        else if (classType.equals(Double.class)) return (S) classType.cast(number.doubleValue());
        else if (classType.equals(Float.class)) return (S) classType.cast(number.floatValue());
        else if (classType.equals(Integer.class)) return (S) classType.cast(number.intValue());
        else if (classType.equals(Long.class)) return (S) classType.cast(number.longValue());
        else if (classType.equals(Short.class)) return (S) classType.cast(number.shortValue());
        else throw new IllegalArgumentException();
    }
}