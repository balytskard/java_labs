package org.example.Exceptions;
//Custom error, which helps us to check the number of elements passed to the method
public class ArgumentAmountException extends Exception {
    public ArgumentAmountException() {
        super("Wrong amount of arguments");
    }
}