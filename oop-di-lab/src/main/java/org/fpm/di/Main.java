package org.fpm.di;

public class Main {
    public static void main(String[] args) {
        try {
            AppContext appContext = new AppContext(AppConfiguration.class);
            OrderService orderService = appContext.getComponent(OrderService.class);
            System.out.println(orderService.getOrderDetails(1));
            System.out.println(orderService.getOrderDetails(2));

            SingletonExampleClass instance1 = appContext.getComponent(SingletonExampleClass.class);
            SingletonExampleClass instance2 = appContext.getComponent(SingletonExampleClass.class);

            System.out.println(instance1 == instance2);
            System.out.println(instance1.equals(instance2));

            System.out.println(instance1.value);
            System.out.println(instance2.value);
            instance2.value = "anotherValue";
            System.out.println(instance2.value);
            System.out.println(instance1);
            System.out.println(instance2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
