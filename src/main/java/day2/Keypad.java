package day2;

import java.util.ArrayList;
import java.util.List;

public class Keypad {


    List<Integer> press(String input) {
        Prep prep = new Prep(input);

        List<List<KeyMove>> moves = prep.getMoves();
        Integer pos = 5;
        List<Integer> results = new ArrayList<>();
        for (List<KeyMove> move : moves) {
            Integer calc = calc(pos, move);
            results.add(calc);
            pos = calc;


        }
        return results;

    }

    Integer calc(Integer start, List<KeyMove> moves) {
        if (moves.isEmpty()) {
            return start;
        }

        Integer newPos = moves.remove(0).move.apply(start);

        return calc(newPos, moves);
    }

}
