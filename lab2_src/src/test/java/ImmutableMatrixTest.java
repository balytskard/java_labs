import org.example.ImmutableMatrix;
import org.example.Matrix;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableMatrixTest {

    @Test
    public void testChangeNumber() {
        assertThrows(UnsupportedOperationException.class, () -> {
            ImmutableMatrix<Integer> matrix = new ImmutableMatrix<>(3, 3, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            matrix.getValues().get(0).set(0, 0);
        });
    }

    @Test
    public void testChangeRow() {
        assertThrows(UnsupportedOperationException.class, () -> {
            ImmutableMatrix<Integer> matrix = new ImmutableMatrix<>(3, 3, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            matrix.getValues().set(0, Arrays.asList(0, 0, 0));
        });
    }

    @Test
    public void testChangeFromCopy() {
        assertThrows(UnsupportedOperationException.class, () -> {
            ImmutableMatrix<Integer> matrix = new ImmutableMatrix<>(new Matrix<>(3, 3, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
            matrix.getValues().set(0, Arrays.asList(0, 0, 0));
        });
    }
}