package day2;

import com.google.common.base.Function;

public enum KeyMove {
    UP(i -> i - 3 > 1 ? i - 3 : i),
    DOWN((i ->
            i + 3 < 10 ? i + 3 : i
    )),
    LEFT((i ->
            (i - 1) % 3 != 0 ? i - 1 : i
    )),
    RIGHT((i ->
            i % 3 != 0 ? i + 1 : i
    ));

    public final Function<Integer, Integer> move;

    KeyMove(final Function<Integer, Integer> move) {
        this.move = move;
    }
}
