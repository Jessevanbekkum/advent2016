package day16;

import java.util.BitSet;
import java.util.stream.IntStream;

public class Dragon {

    private static String iv;
    private final int length;
    private BitSet disk;

    Dragon(int length, String iv) {
        this.length = length;
        disk = fromString(iv);
    }


    public static BitSet fromString(String s) {
        iv = s;
        BitSet result = new BitSet();
        for (int i = 0; i < s.length(); i++) {
            result.set(i, s.charAt(i) == '1');
        }

        return result;
    }

    void dragonCurve() {
        int p = iv.length();
        while (p < length) {
            BitSet copy = new BitSet(p);

            for (int i = 0; i < p; i++) {
                copy.set(i, !disk.get(p - i - 1));
            }

            final int pp = p + 1;
            IntStream.range(0, p).forEach(i -> disk.set(pp + i, copy.get(i)));
            p = 2 * p + 1;
        }


    }

    public String checksum() {
        return checksum(disk, length);
    }

    public String checksum(BitSet bitSet, int l) {
        if (l % 2 == 1) {
            return printBitSet(bitSet, l);
        }
        BitSet result = new BitSet();
        IntStream.iterate(0, i -> i + 2)
                .limit(l / 2)
                .forEach(i -> result.set(i / 2, bitSet.get(i) == bitSet.get(i + 1)));

        return checksum(result, l / 2);
    }

    @Override
    public String toString() {
        return printBitSet(disk, length);
    }

    public String printBitSet(BitSet bitSet, int l) {
        StringBuilder sb = new StringBuilder();

        IntStream.range(0, l).forEach(i -> {
            if (bitSet.get(i)) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        });

        return sb.toString();
    }
}
