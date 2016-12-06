package day6;

import java.util.*;
import java.util.stream.IntStream;

public class Histogram {

    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    List<Map<Character, Integer>> gram = new ArrayList<>();

    Histogram(int length) {
        IntStream.range(0, length).forEach(x -> gram.add(new HashMap<>()));
        gram.forEach(m -> alphabet.chars().forEach(c -> m.put((char) c, 0)));
    }

    public void addLine(String s) {
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            gram.get(i).put(key, gram.get(i).get(key) + 1);
        }
    }

    public String getPasswordMostCommon() {
        return gram.stream().map(m -> getMax(m)).collect(StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append)
                .toString();
    }


    public String getPasswordLeastCommon() {
        return gram.stream().map(this::getMin).map(Optional::get).collect(StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append)
                .toString();
    }

    public Character getMax(Map<Character, Integer> map) {
        OptionalInt max = map.values().stream().mapToInt(i -> i).max();
        if (max.isPresent()) {
            return map.entrySet().stream().filter(e -> e.getValue() == max.getAsInt()).findFirst().get().getKey();
        }
        return ' ';
    }

    public Optional<Character> getMin(Map<Character, Integer> map) {
        OptionalInt min = map.values().stream().filter(i -> i != 0).mapToInt(i -> i).min();
        if (min.isPresent()) {
            Character key =
                    map.entrySet().stream().filter(e -> e.getValue() == min.getAsInt()).findFirst().get().getKey();
            return Optional.of(key);
        }
        return Optional.empty();
    }
}
