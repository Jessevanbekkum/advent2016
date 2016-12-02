package day2;

import java.util.List;
import org.junit.Test;
import com.google.common.collect.Lists;

import static day2.KeyMove.DOWN;
import static day2.KeyMove.LEFT;
import static day2.KeyMove.RIGHT;
import static day2.KeyMove.UP;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class KeypadTest {

    @Test
    public void shouldDo8() {
        Keypad keypad = new Keypad();
        List<KeyMove> moves = Lists.newArrayList(LEFT, UP, RIGHT, DOWN, LEFT);
        Integer result = keypad.calc(9, moves);

        assertThat(result, is(8));
    }
}
