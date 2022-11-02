package org.example.Exceptions;
//Custom error, which helps us to check if the matrix is full
public class NotFilledMatrixException extends Exception {
    public NotFilledMatrixException() {
        super("Matrix is not filled");
    }
}