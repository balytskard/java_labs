import org.example.Exceptions.NotFilledMatrixException;
import org.example.Matrix;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    public void testTransposed() {
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(3, 2, Arrays.asList(1, 2, 3, 4, 5, 6)).transposed(), new Matrix<>(2, 3, Arrays.asList(1, 3, 5, 2, 4, 6))));
    }

    @Test
    public void testTransposedThrows() {
        assertThrows(NotFilledMatrixException.class, () -> new Matrix<>(2, 3).transposed());
    }

    @Test
    public void testIdentity() {
        assertDoesNotThrow(() -> assertEquals(Matrix.identity(Integer.class, -1), new Matrix<>()));
        assertDoesNotThrow(() -> assertEquals(Matrix.identity(Integer.class, 3), new Matrix<>(3, 3, Arrays.asList(1, 0, 0, 0, 1, 0, 0, 0, 1))));
    }


    @Test
    public void testGetNumber() {
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).getNumber(1, 1), 4));
    }

    @Test
    public void testGetNumberThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).getNumber(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).getNumber(3, 0));
        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).getNumber(0, -1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).getNumber(0, 3));
        assertThrows(NotFilledMatrixException.class, () -> new Matrix<>(2, 2).getNumber(0, 0));
    }

    @Test
    public void testGetRow() {
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).getRow(0), Arrays.asList(1, 2)));
    }

    @Test
    public void testGetRowThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).getRow(-1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).getRow(3));
        assertThrows(NotFilledMatrixException.class, () -> new Matrix<>(2, 2).getRow(0));
    }

    @Test
    public void testGetColumn() {
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).getColumn(0), Arrays.asList(1, 3)));
    }

    @Test
    public void testGetColumnThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).getColumn(-1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).getColumn(3));
        assertThrows(NotFilledMatrixException.class, () -> new Matrix<>(2, 2).getColumn(0));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testEquals() {
        assertDoesNotThrow(() -> {
            Matrix<Integer> matrix = new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4));
            assertEquals(matrix, matrix);
        });
        assertDoesNotThrow(() -> assertNotEquals(new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)), Arrays.asList(1, 2, 3, 4)));
        assertDoesNotThrow(() -> assertNotEquals(new Matrix<>(2, 2), new Matrix<>(3, 2)));
        assertDoesNotThrow(() -> assertNotEquals(new Matrix<>(2, 2), new Matrix<>(2, 3)));
        assertDoesNotThrow(() -> assertNotEquals(new Matrix<>(2, 2).getSize(), new Matrix<>(3, 2).getSize()));
        assertDoesNotThrow(() -> assertNotEquals(new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)), new Matrix<>(2, 2)));
        assertDoesNotThrow(() -> assertNotEquals(new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)), new Matrix<>(2, 2, Arrays.asList(1f, 2f, 3f, 4f))));
        assertDoesNotThrow(() -> assertNotEquals(new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)), new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 5))));
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)), new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4))));
    }

    @Test
    public void testHashCode() {
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).hashCode(), 32833));
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(2, 2, Arrays.asList(3, 4, 1, 2)).hashCode(), 34753));
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(2, 2, Arrays.asList(4, 3, 2, 1)).hashCode(), 35713));
    }

    @Test
    public void testGetRowVar() {
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(2, 3).getRows(), 2));
    }

    @Test
    public void testGetColumnVar() {
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(3, 2).getColumns(), 2));
    }

    @Test
    public void testGetSizeVar() {
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(3, 2).getSize(), 6));
    }

    @Test
    public void testGetSizeStringVar() {
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(3, 2).getSizeToString(), "3 x 2"));
    }

    @Test
    public void testGetTypeVar() {
        assertDoesNotThrow(() -> assertEquals(new Matrix<>(2, 2, Arrays.asList(1f, 2f, 3f, 4f)).getType(), Float.class));
    }

    @Test
    public void testGetValuesVar() {
        assertDoesNotThrow(() -> {
            List<List<Float>> testList = new ArrayList<>();
            testList.add(new ArrayList<>(Arrays.asList(1f, 2f)));
            testList.add(new ArrayList<>(Arrays.asList(3f, 4f)));
            assertEquals(new Matrix<>(2, 2, Arrays.asList(1f, 2f, 3f, 4f)).getValues(), testList);
        });
    }

    @Test
    public void testIsFilled() {
        assertDoesNotThrow(() -> assertFalse(new Matrix<>(2, 2).isFilled()));
        assertDoesNotThrow(() -> assertTrue(new Matrix<>(2, 2, Arrays.asList(1, 2, 3, 4)).isFilled()));
    }
}
