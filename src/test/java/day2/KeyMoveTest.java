package day2;



import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeyMoveTest {

    private void test(KeyMove m, int v, Integer e) {
        assertEquals(m.move.apply(v), e);
    }

    @Test
    public void testDown() {
        test(KeyMove.DOWN, 8, 8);
        test(KeyMove.DOWN, 1, 4);
        test(KeyMove.DOWN, 9, 9);
        test(KeyMove.DOWN, 3, 6);
        test(KeyMove.DOWN, 7, 7);
    }

    @Test
    public void testUp() {
        test(KeyMove.UP, 1, 1);
        test(KeyMove.UP, 2, 2);
        test(KeyMove.UP, 3, 3);
        test(KeyMove.UP, 6, 3);
        test(KeyMove.UP, 8, 5);
    }


    @Test
    public void testLeft() {
        test(KeyMove.LEFT, 1, 1);
        test(KeyMove.LEFT, 4, 4);
        test(KeyMove.LEFT, 7, 7);
        test(KeyMove.LEFT, 6, 5);
        test(KeyMove.LEFT, 9, 8);
    }
}
