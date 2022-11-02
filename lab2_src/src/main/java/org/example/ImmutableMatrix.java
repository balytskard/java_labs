package org.example;

import org.example.Exceptions.ArgumentAmountException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ImmutableMatrix<T extends Number> extends Matrix<T> {

    public ImmutableMatrix(int row, int column, List<T> numbers) throws ArgumentAmountException, IllegalArgumentException {
        if (row < 0 || column < 0) throw new IllegalArgumentException();
        if (numbers.size() != row * column) throw new ArgumentAmountException();

        type = numbers.get(0).getClass();

        this.row = row;
        this.column = column;
        size = row * column;
        filled = true;

        List<List<T>> tempRow = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < row; i++) {
            List<T> tempColumn = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                if (!numbers.get(i).getClass().equals(type)) throw new IllegalArgumentException();
                tempColumn.add(numbers.get(counter));
                counter++;
            }
            tempRow.add(Collections.unmodifiableList(tempColumn));
        }
        values = Collections.unmodifiableList(tempRow);
    }

    public ImmutableMatrix(Matrix<T> matrix) {
        this.row = matrix.row;
        this.column = matrix.column;
        size = row * column;
        filled = matrix.filled;

        List<List<T>> tempRow = new ArrayList<>();
        for (int i = 0; i < row; i++) tempRow.add(Collections.unmodifiableList(matrix.values.get(i)));
        values = Collections.unmodifiableList(tempRow);
    }
}