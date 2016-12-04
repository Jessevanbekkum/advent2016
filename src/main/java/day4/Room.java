package day4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Room {

    private class Pair implements Comparable<Pair> {
        final Character c;
        final Integer i;

        private Pair(final Character c, final Integer i) {
            this.c = c;
            this.i = i;
        }

        Integer getI() {
            return i;
        }

        Character getC() {
            return c;
        }

        @Override
        public int compareTo(final Pair o) {
            return Comparator.comparingInt(Pair::getI).reversed().thenComparing(Pair::getC).compare(this, o);
        }
    }

    public final String checksum;
    public final int sector;
    public final String cryptedName;

    public final HashMap<Character, Integer> hashMap = new HashMap<>();

    Pattern pattern = Pattern.compile("([a-z\\-]*)(\\d*)\\[([a-z]*)]");

    Room(String s) {
        Matcher matcher = pattern.matcher(s);
        matcher.matches();
        cryptedName = matcher.group(1);
        sector = Integer.parseInt(matcher.group(2));
        checksum = matcher.group(3);
    }

    boolean checksumMatches() {

        for (char c : cryptedName.replaceAll("-", "").toCharArray()) {
            hashMap.computeIfPresent(c, (d, i) -> i + 1);
            hashMap.putIfAbsent(c, 1);
        }

        List<Character> chars = hashMap.entrySet().stream().map(e -> new Pair(e.getKey(), e.getValue())).sorted().map(Pair::getC)
                .collect(Collectors.toList()).subList(0, 5);

        StringBuffer sb = new StringBuffer();
        chars.forEach(sb::append);

        return sb.toString().equals(checksum);
    }

    public Integer getSector() {
        return sector;
    }

    public String decrypt() {
        StringBuffer result = new StringBuffer();
        int steps = sector % 26;
        for (Character c : cryptedName.toCharArray()) {
            if (c.equals('-')) {
                result.append(' ');
                continue;
            }
            int ascii = (int) c;
            result.append(shift(steps, ascii));
        }
        return result.toString().trim();
    }

    public static Character shift(final int steps, final int ascii) {
        char c = (char) ((ascii - 97 + steps) % 26 + 97);
        return c;
    }
}
