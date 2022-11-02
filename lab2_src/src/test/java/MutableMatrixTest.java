import org.example.Matrix;
import org.example.MutableMatrix;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MutableMatrixTest {

    @Test
    public void testEmptyConstructor() {
        MutableMatrix<Integer> matrix = new MutableMatrix<>();

        assertEquals(matrix.getValues(), new ArrayList<>());
    }

    @Test
    public void testRowColumnEmptyConstructor() {
        MutableMatrix<Integer> matrix = new MutableMatrix<>(3, 3);

        List<List<Integer>> emptyList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ArrayList<Integer> emptyRow = new ArrayList<>();
            for (int j = 0; j < 3; j++) emptyRow.add(null);
            emptyList.add(emptyRow);
        }

        assertEquals(matrix.getValues(), emptyList);
    }

    @Test
    public void testConstructor() {
        assertDoesNotThrow(() -> {
            MutableMatrix<Integer> matrix = new MutableMatrix<>(3, 3, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

            List<List<Integer>> testList = new ArrayList<>();
            testList.add(Arrays.asList(1, 2, 3));
            testList.add(Arrays.asList(4, 5, 6));
            testList.add(Arrays.asList(7, 8, 9));

            assertEquals(matrix.getValues(), testList);
        });
    }

    @Test
    public void testConstructorThrows() {
        assertThrows(Exception.class, () -> new MutableMatrix<>(3, 3, Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertThrows(Exception.class, () -> new MutableMatrix<>(3, 3, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)));
    }

    @Test
    public void testCopyConstructor() {
        assertDoesNotThrow(() -> {
            MutableMatrix<Integer> matrix = new MutableMatrix<>(new Matrix<>(3, 3, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));

            List<List<Integer>> testList = new ArrayList<>();
            testList.add(Arrays.asList(1, 2, 3));
            testList.add(Arrays.asList(4, 5, 6));
            testList.add(Arrays.asList(7, 8, 9));

            assertEquals(matrix.getValues(), testList);
        });
    }

    @Test
    void testCopyConstructorNotFilled() {
        assertDoesNotThrow(() -> new MutableMatrix<>(new Matrix<>()));
    }

    @Test
    public void testFillMatrix() {
        assertDoesNotThrow(() -> {
            MutableMatrix<Integer> matrix = new MutableMatrix<>(3, 3);

            matrix.fillMatrix(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

            List<List<Integer>> testList = new ArrayList<>();
            testList.add(Arrays.asList(1, 2, 3));
            testList.add(Arrays.asList(4, 5, 6));
            testList.add(Arrays.asList(7, 8, 9));

            assertEquals(matrix.getValues(), testList);
        });
    }

    @Test
    public void testFillMatrixThrows() {
        MutableMatrix<Integer> matrix = new MutableMatrix<>(3, 3);

        assertThrows(Exception.class, () -> matrix.fillMatrix(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertThrows(Exception.class, () -> matrix.fillMatrix(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)));
    }

    @Test
    public void testTranspose() {
        assertDoesNotThrow(() -> {
            MutableMatrix<Integer> matrix = new MutableMatrix<>(3, 2, Arrays.asList(1, 2, 3, 4, 5, 6));
            matrix.transpose();
            assertEquals(matrix, new MutableMatrix<>(2, 3, Arrays.asList(1, 3, 5, 2, 4, 6)));
        });
    }

    @Test
    public void testTransposeThrows() {
        assertThrows(Exception.class, () -> new MutableMatrix<>(3, 3).transpose());
    }

    @Test
    public void testSetNumber() {
        assertDoesNotThrow(() -> {
            MutableMatrix<Integer> matrix = new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4));
            matrix.setNumber(0, 0, 0);
            assertEquals(matrix, new MutableMatrix<>(2, 2, Arrays.asList(0, 2, 3, 4)));
        });
    }

    @Test
    public void testSetNumberThrows() {
        assertThrows(Exception.class, () -> new MutableMatrix<>(2, 3).setNumber(1, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).setNumber(-1, 1, 2));
        assertThrows(IllegalArgumentException.class, () -> new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).setNumber(4, 1, 2));
        assertThrows(IllegalArgumentException.class, () -> new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).setNumber(1, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).setNumber(1, 4, 2));
    }

    @Test
    public void testSetRow() {
        assertDoesNotThrow(() -> {
            MutableMatrix<Integer> matrix = new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4));
            matrix.setRow(0, Arrays.asList(0, 0));
            assertEquals(matrix, new MutableMatrix<>(2, 2, Arrays.asList(0, 0, 3, 4)));
        });
    }

    @Test
    public void testSetRowThrows() {
        assertThrows(Exception.class, () -> new MutableMatrix<>(2, 2).setRow(1, Arrays.asList(1, 2)));
        assertThrows(IllegalArgumentException.class, () -> new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).setRow(-1, Arrays.asList(1, 2)));
        assertThrows(IllegalArgumentException.class, () -> new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).setRow(4, Arrays.asList(1, 2)));
        assertThrows(Exception.class, () -> new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).setRow(1, Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testSetColumn() {
        assertDoesNotThrow(() -> {
            MutableMatrix<Integer> matrix = new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4));
            matrix.setColumn(0, Arrays.asList(0, 0));
            assertEquals(matrix, new MutableMatrix<>(2, 2, Arrays.asList(0, 2, 0, 4)));
        });
    }

    @Test
    public void testSetColumnThrows() {
        assertThrows(Exception.class, () -> new MutableMatrix<>(2, 3).setColumn(1, Arrays.asList(1, 2)));
        assertThrows(IllegalArgumentException.class, () -> new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).setColumn(-1, Arrays.asList(1, 2)));
        assertThrows(IllegalArgumentException.class, () -> new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).setColumn(4, Arrays.asList(1, 2)));
        assertThrows(Exception.class, () -> new MutableMatrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).setColumn(1, Arrays.asList(1, 2, 3)));
    }
}