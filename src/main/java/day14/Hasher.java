package day14;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.codec.binary.Hex;

public class Hasher {
    private final MessageDigest digest;
    private final String[] hashes = new String[1000000];
    private final List<Tuple> results = new ArrayList<>();
    private final String input;
    private final int factor;
    private int index;

    private static class Tuple {
        final int index;
        final char character;

        private Tuple(final int index, final char character) {
            this.index = index;
            this.character = character;
        }

        static Tuple of(int index, char character) {
            return new Tuple(index, character);
        }

    }

    public Hasher(String input) {
        this(input, 1);
    }

    public Hasher(String input, int factor) {
        this(input, factor, "MD5");
    }
    public Hasher(String input, int factor, String algorithm) {
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

        }
        this.input = input;
        index = 0;
        this.factor = factor;
        calcNextThousand(0);
    }

    public static Hasher sha256Hasher(String input, int factor) {
        return new Hasher(input, factor, "SHA-256");
    }

    public int calc() {
        while (results.size() < 64) {
            calcNextThousand(index + 1000);
            checkNextThousand(index);

            index += 1000;
        }
        results.forEach(System.out::println);
        return results.get(63).index;
    }

    private void checkNextThousand(int index) {
        List<Tuple> candidates = IntStream.range(index, index + 1000)
                .filter(i -> contains3(hashes[i]).isPresent())
                .mapToObj(i -> Tuple.of(i, contains3(hashes[i]).get()))
                .collect(Collectors.toList());

        List<Tuple> finalists = candidates.stream().filter(this::has5TimesCharInNext1000)
                .collect(Collectors.toList());

        results.addAll(finalists);
    }

    private boolean has5TimesCharInNext1000(Tuple tuple) {
        return IntStream.range(tuple.index + 1, tuple.index + 1000)
                .anyMatch(j -> contains5Of(hashes[j], tuple.character));
    }

    private void calcNextThousand(int index) {
        IntStream.range(index, index + 1000).forEach(i -> hashes[i] = hash(i));
    }

    String hash(int index) {
        final String[] s = {input + index};

        IntStream.range(0, factor + 1).forEach(
                i -> s[0] = String.valueOf(Hex.encodeHex(digest.digest(s[0].getBytes())))
        );
        return s[0];
    }

    private Optional<Character> contains3(String s) {
        return containsX(s, 3);
    }

    private boolean contains5Of(String haystack, char needle) {
        Optional<Character> character = containsX(haystack, 5);
        return character.isPresent() && character.get() == needle;
    }

    private Optional<Character> containsX(String s, int x) {
        char current = 'h';
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == current) {
                count++;
            } else {
                current = c;
                count = 1;
            }
            if (count == x) {
                return Optional.of(current);
            }
        }
        return Optional.empty();
    }
}
