package day20;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Firewall {

    class Pair implements Comparable<Pair> {
        long from;
        long to;

        public Pair(final long from, final long to) {
            this.from = from;
            this.to = to;


        }

        @Override
        public int compareTo(final Pair o) {
            return Long.compare(from, o.from);
        }
    }

    Pattern pattern = Pattern.compile("(\\d+)-(\\d+)");

    public long input(List<String> list) {
        List<Pair> sorted = list.stream()
                .map(pattern::matcher)
                .filter(Matcher::find)
                .map(m -> new Pair(Long.valueOf(m.group(1))
                        , Long.valueOf(m.group(2)))).sorted().collect(Collectors.toList());

        long candidate = 0;
        for (Pair pair : sorted) {
            if (pair.from > candidate) {
                return candidate;
            } else {
                candidate = Math.max(candidate, pair.to + 1);
            }
        }

        return Integer.MAX_VALUE;
    }

    public long nrOfIps(List<String> list) {
        List<Pair> sorted = list.stream()
                .map(pattern::matcher)
                .filter(Matcher::find)
                .map(m -> new Pair(Long.valueOf(m.group(1))
                        , Long.valueOf(m.group(2)))).sorted().collect(Collectors.toList());

        long sum = 0;
        long pointer = 0;
        for (Pair pair : sorted) {
            if (pair.from > pointer) {
                sum += pair.from - pointer;
            }
            pointer = Math.max(pointer, pair.to + 1);

        }

        return sum;
    }
}
