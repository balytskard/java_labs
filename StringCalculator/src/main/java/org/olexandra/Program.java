package org.olexandra;

public class Program {

    public static void main(String[] args) {

        final StringCalculator calculator = new StringCalculator();

        String a = "//abobus\n2abobus2";

        try {
            System.out.println("Input:\n" + a);
            int n = calculator.add(a);
            System.out.println("Result: " + n);
        } catch (Exception exception) {
            exception.printStackTrace(System.out);
        }

    }

}
