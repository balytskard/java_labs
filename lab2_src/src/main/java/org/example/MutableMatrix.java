package org.example;

import org.example.Exceptions.NotFilledMatrixException;
import org.example.Exceptions.ArgumentAmountException;

import java.util.ArrayList;
import java.util.List;

public class MutableMatrix<T extends Number> extends Matrix<T> {

    public MutableMatrix() {
        super();
    }

    public MutableMatrix(int row, int column) {
        super(row, column);
    }

    public MutableMatrix(int row, int column, List<T> numbers) throws ArgumentAmountException, IllegalArgumentException {
        super(row, column, numbers);
    }

    public MutableMatrix(Matrix<T> matrix) throws NotFilledMatrixException {
        super(matrix);
    }

    public void fillMatrix(List<T> numbers) throws ArgumentAmountException, IllegalArgumentException {
        if (numbers.size() != row * column) throw new ArgumentAmountException();

        type = numbers.get(0).getClass();

        filled = true;

        int counter = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++) {
                if (!numbers.get(i).getClass().equals(type)) throw new IllegalArgumentException();
                values.get(i).set(j, numbers.get(counter));
                counter++;
            }
    }

    public void transpose() throws NotFilledMatrixException {
        if (!filled) throw new NotFilledMatrixException();

        List<List<T>> newValues = new ArrayList<>();

        for (int j = 0; j < column; j++) {
            List<T> column = new ArrayList<>();
            for (int i = 0; i < row; i++) column.add(values.get(i).get(j));
            newValues.add(column);
        }

        values = newValues;
        int temp = row;
        row = column;
        column = temp;
    }

    public void setNumber(int row, int column, T number) throws NotFilledMatrixException, IllegalArgumentException {
        if (!filled) throw new NotFilledMatrixException();
        if (this.row < row || this.column < column || row < 0 || column < 0) throw new IllegalArgumentException();
        if (number.getClass().equals(type))
            values.get(row).set(column, number);
    }

    public void setRow(int row, List<T> numbers) throws NotFilledMatrixException, IllegalArgumentException, ArgumentAmountException {
        if (!filled) throw new NotFilledMatrixException();
        if (this.row < row || row < 0) throw new IllegalArgumentException();
        if (column != numbers.size()) throw new ArgumentAmountException();
        for (T number : numbers) if (!number.getClass().equals(type)) throw new IllegalArgumentException();
        values.set(row, numbers);
    }

    public void setColumn(int column, List<T> numbers) throws NotFilledMatrixException, IllegalArgumentException, ArgumentAmountException {
        if (!filled) throw new NotFilledMatrixException();
        if (this.column < column || column < 0) throw new IllegalArgumentException();
        if (row != numbers.size()) throw new ArgumentAmountException();
        int counter = 0;
        for (List<T> list : values) {
            if (!numbers.get(counter).getClass().equals(type)) throw new IllegalArgumentException();
            list.set(column, numbers.get(counter));
            counter++;
        }
    }
}