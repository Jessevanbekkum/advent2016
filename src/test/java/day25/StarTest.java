package day25;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Test;

public class StarTest {

    @Test
    public void doInput() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("input.txt").toURI()));
        IntStream.range(0, 2532).forEach(a -> {
            try {
                System.out.println();
                System.out.println("*******" + a + "**********");
                new Star(strings, a);
            } catch (IllegalStateException e) {
                System.out.println(a + " was wrong");
            } catch (UnsupportedOperationException uoe) {

            }
        });
    }

    @Test
    public void doInput2() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("input.txt").toURI()));
        new Star(strings, 196);
    }

}
