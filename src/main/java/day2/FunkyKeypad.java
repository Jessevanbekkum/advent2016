package day2;

import java.util.ArrayList;
import java.util.List;

public class FunkyKeypad {


    List<Character> press(String input) {
        FunkyPrep prep = new FunkyPrep(input);

        List<List<FunkyKeyMove>> moves = prep.getMoves();
        Character pos = '5';
        List<Character> results = new ArrayList<>();
        for (List<FunkyKeyMove> move : moves) {
            Character calc = calc(pos, move);
            results.add(calc);
            pos = calc;


        }
        return results;

    }

    Character calc(Character start, List<FunkyKeyMove> moves) {
        if (moves.isEmpty()) {
            return start;
        }

        Character newPos = moves.remove(0).move.apply(start);

        return calc(newPos, moves);
    }

}
