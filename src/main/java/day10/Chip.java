package day10;

public class Chip implements Comparable<Chip> {

    final int value;

    public int getValue() {
        return value;
    }

    public Chip(final int value) {
        this.value = value;
    }

    @Override
    public int compareTo(final Chip o) {
        return Integer.compare(this.value, o.value);

    }
}
