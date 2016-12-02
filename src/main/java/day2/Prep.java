package day2;

import java.util.*;
import java.util.stream.Collectors;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class Prep {

    private final List<List<KeyMove>> moves;

    public List<List<KeyMove>> getMoves() {
        return moves;
    }

    private final Map<Character, KeyMove> lookup = new HashMap<>();


    public Prep(String input) {
        lookup.put('U', KeyMove.UP);
        lookup.put('D', KeyMove.DOWN);
        lookup.put('L', KeyMove.LEFT);
        lookup.put('R', KeyMove.RIGHT);

        List<String> strings = Splitter.on('\n').splitToList(input);
        moves = strings.stream()
                .map(Lists::charactersOf)
                .map(this::parseLine)
                .collect(Collectors.toList());
    }

    private List<KeyMove> parseLine(List<Character> chars) {
        List<KeyMove> list = new ArrayList<>();
        for (char c : chars) {
            list.add(lookup.get(c));
        }

        return list;
    }


}
