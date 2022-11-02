package org.example;

import org.example.Exceptions.NotFilledMatrixException;
import org.example.Exceptions.ArgumentAmountException;

import java.util.ArrayList;
import java.util.List;

import static org.example.Utility.*;

public class Matrix<T extends Number> {

    protected Class<? extends Number> type;

    protected int row;
    protected int column;
    protected int size;

    protected List<List<T>> values;
    protected boolean filled;

    public Matrix() {
        type = Integer.class;

        row = 0;
        column = 0;
        size = 0;
        filled = false;

        values = new ArrayList<>();
    }

    public Matrix(int row, int column) {
        if (row < 0 || column < 0) throw new IllegalArgumentException();

        type = Integer.class;

        this.row = row;
        this.column = column;
        size = row * column;
        filled = false;

        values = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            values.add(new ArrayList<>());
            for (int j = 0; j < column; j++) values.get(i).add(null);
        }
    }

    public Matrix(int row, int column, List<T> numbers) throws ArgumentAmountException, IllegalArgumentException {
        if (row < 0 || column < 0) throw new IllegalArgumentException();
        if (numbers.size() != row * column) throw new ArgumentAmountException();

        type = numbers.get(0).getClass();

        this.row = row;
        this.column = column;
        size = row * column;
        filled = true;

        values = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < row; i++) {
            values.add(new ArrayList<>());
            for (int j = 0; j < column; j++) {
                if (!numbers.get(i).getClass().equals(type)) throw new IllegalArgumentException();
                values.get(i).add(numbers.get(counter));
                counter++;
            }
        }
    }

    public Matrix(Matrix<T> matrix) throws NotFilledMatrixException {
        type = matrix.type;

        row = matrix.row;
        column = matrix.column;
        size = matrix.size;
        filled = matrix.filled;

        values = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            values.add(new ArrayList<>());
            for (int j = 0; j < column; j++) values.get(i).add(matrix.getNumber(i, j));
        }
    }

    public Matrix<T> transposed() throws NotFilledMatrixException, ArgumentAmountException {
        if (!filled) throw new NotFilledMatrixException();

        ArrayList<T> newValues = new ArrayList<>();
        for (int j = 0; j < column; j++) for (int i = 0; i < row; i++) newValues.add(values.get(i).get(j));

        return new Matrix<>(column, row, newValues);
    }

    public static <R extends Number> Matrix<R> identity (Class<R> valueType, int size) throws ArgumentAmountException, IllegalArgumentException {
        if (size <= 0) return new Matrix<>();

        ArrayList<R> values = new ArrayList<>();
        R one = castNumberToGeneric(valueType, 1);
        R zero = castNumberToGeneric(valueType, 0);

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    values.add(one);
                } else values.add(zero);
            }

        return new Matrix<>(size, size, values);
    }

    public T getNumber(int row, int column) throws NotFilledMatrixException, IllegalArgumentException {
        if (this.row < row || this.column < column || row < 0 || column < 0) throw new IllegalArgumentException();
        if (!filled) throw new NotFilledMatrixException();
        return values.get(row).get(column);
    }

    public List<T> getRow(int row) throws NotFilledMatrixException, IllegalArgumentException {
        if (this.row < row || row < 0) throw new IllegalArgumentException();
        if (!filled) throw new NotFilledMatrixException();
        return values.get(row);
    }

    public List<T> getColumn(int column) throws NotFilledMatrixException, IllegalArgumentException {
        if (this.column < column || column < 0) throw new IllegalArgumentException();
        if (!filled) throw new NotFilledMatrixException();
        List<T> columnNumbers = new ArrayList<>();
        for (List<T> list : values) columnNumbers.add(list.get(column));
        return columnNumbers;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Matrix<?> matrix)) return false;
        if (row != matrix.row || column != matrix.column || size != matrix.size || filled != matrix.filled || !type.equals(matrix.type))
            return false;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++)
                if (!values.get(i).get(j).equals(matrix.getValues().get(i).get(j))) return false;
        return true;
    }

    @Override
    public int hashCode() {
        if (row == 0 || column == 0 || size == 0 || !filled) return 0;

        int hash = 1;
        for (List<T> list : values) hash = 31 * hash + list.hashCode();
        return hash;
    }

    public void print() {
        for (List<T> list : values) System.out.println(list.toString());
        System.out.println();
    }

    public final int getRows() {
        return row;
    }

    public final int getColumns() {
        return column;
    }

    public final int getSize() {
        return size;
    }

    public final String getSizeToString() {
        return row + " x " + column;
    }

    public final Class<? extends Number> getType() {
        return type;
    }

    public final List<List<T>> getValues() {
        return values;
    }

    public final boolean isFilled() {
        return filled;
    }
}