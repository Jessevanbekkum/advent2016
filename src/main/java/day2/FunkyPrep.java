package day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class FunkyPrep {

    private final List<List<FunkyKeyMove>> moves;

    public List<List<FunkyKeyMove>> getMoves() {
        return moves;
    }

    private final Map<Character, FunkyKeyMove> lookup = new HashMap<>();


    public FunkyPrep(String input) {
        lookup.put('U', FunkyKeyMove.UP);
        lookup.put('D', FunkyKeyMove.DOWN);
        lookup.put('L', FunkyKeyMove.LEFT);
        lookup.put('R', FunkyKeyMove.RIGHT);

        List<String> strings = Splitter.on('\n').splitToList(input);
        moves = strings.stream()
                .map(Lists::charactersOf)
                .map(this::parseLine)
                .collect(Collectors.toList());
    }

    private List<FunkyKeyMove> parseLine(List<Character> chars) {
        List<FunkyKeyMove> list = new ArrayList<>();
        for (char c : chars) {
            list.add(lookup.get(c));
        }

        return list;
    }


}
