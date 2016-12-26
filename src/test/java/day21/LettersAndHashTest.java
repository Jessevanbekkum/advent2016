package day21;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LettersAndHashTest {

    @Test
    public void rotateTest() {
        LettersAndHash lettersAndHash = new LettersAndHash("abcde");
        String s = lettersAndHash.rotateOne("abcde", "left", 1);
        assertThat(s, is("bcdea"));
    }

    @Test
    public void rotateRight() {
        LettersAndHash lettersAndHash = new LettersAndHash("abcde");
        String s = lettersAndHash.rotateOne("abcde", "right", 2);
        assertThat(s, is("deabc"));
    }

    @Test
    public void shouldDoSwap() throws URISyntaxException, IOException {
        LettersAndHash lettersAndHash = new LettersAndHash("abcde");
        lettersAndHash.swapPositionAction.accept("swap position 4 with position 0");
        assertThat(lettersAndHash.getInput(), is("ebcda"));
    }

    @Test
    public void shouldRotate() throws URISyntaxException, IOException {
        LettersAndHash lettersAndHash = new LettersAndHash("abcdefgh");
        System.out.println(lettersAndHash.rotate.matcher("rotate right 3 steps").matches());
        lettersAndHash.rotateAction.accept("rotate right 3 steps");
        assertThat(lettersAndHash.getInput(), is("fghabcde"));
    }


    @Test
    public void shouldRotateLeft() throws URISyntaxException, IOException {
        LettersAndHash lettersAndHash = new LettersAndHash("abcdefgh");
        System.out.println(lettersAndHash.rotate.matcher("rotate right 3 steps").matches());
        lettersAndHash.rotateAction.accept("rotate right 3 steps");
        assertThat(lettersAndHash.getInput(), is("fghabcde"));
    }

    @Test
    public void shouldDoLetterSwap() throws URISyntaxException, IOException {
        LettersAndHash lettersAndHash = new LettersAndHash("ebcda");
        lettersAndHash.swapLetterAction.accept("swap letter d with letter b");
        assertThat(lettersAndHash.getInput(), is("edcba"));
    }


    @Test
    public void shouldDoPositionSwap() throws URISyntaxException, IOException {
        LettersAndHash lettersAndHash = new LettersAndHash("ahgdcfbe");
        lettersAndHash.reverseAction.accept("reverse positions 3 through 4");
        assertThat(lettersAndHash.getInput(), is("ahgcdfbe"));
    }

    @Test
    public void shouldDoExample() throws URISyntaxException, IOException {
        LettersAndHash lettersAndHash = new LettersAndHash("abcde");
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("example.txt").toURI()));
        strings.forEach(lettersAndHash::applyLine);
        assertThat(lettersAndHash.getInput(), is("decab"));
    }


    @Test
    public void shouldDoInput() throws URISyntaxException, IOException {
        LettersAndHash lettersAndHash = new LettersAndHash("abcdefgh");
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("day22/input.txt").toURI()));
        strings.forEach(lettersAndHash::applyLine);
        assertThat(lettersAndHash.getInput(), is("decab"));
    }

    @Test
    public void shouldDoReverse() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("day22/input.txt").toURI()));

        List<String> abcdefgh = generator("abcdefgh");
        abcdefgh.forEach(s -> {
            LettersAndHash lah = new LettersAndHash(s);
            strings.forEach(lah::applyLine);
            if (lah.getInput().equals("fbgdceah")){
                System.out.println(s);
                System.exit(0);
            };
        });
    }

    @Test
    public void shouldGenerate() {
        List<String> abcdefgh = generator("abcdefgh");
        assertThat(abcdefgh.size(), is(40320));
    }

    @Test
    public void shouldGenerateA() {
        List<String> abcdefgh = generator("a");
        assertThat(abcdefgh.size(), is(1));
    }

    private List<String> generator(String source) {
        if (source.equals("")) {
            return Collections.singletonList("");
        }
        List<String> result = new ArrayList<>();
        for (char c : source.toCharArray()) {
            String s1 = source.replaceAll(String.valueOf(c), "");
            generator(s1)
                    .forEach(s -> {
                        result.add(c + s);
                    });
        }

        return result;
    }
}
