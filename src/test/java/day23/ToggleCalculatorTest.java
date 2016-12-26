package day23;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToggleCalculatorTest {
    @Test
    public void doExample() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("example.txt").toURI()));
        Integer calculate = ToggleCalculator.calculate(strings, Collections.emptyMap());

        assertEquals(Integer.valueOf(3), calculate);
    }

    @Test
    public void doInput() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("input.txt").toURI()));
        Integer calculate = ToggleCalculator.calculate(strings, Collections.singletonMap("a", 7));

        assertEquals(Integer.valueOf(3), calculate);
    }

    @Test
    public void doInput2() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("input2.txt").toURI()));
        Integer calculate = ToggleCalculator.calculate(strings, Collections.singletonMap("a", 12));

        assertEquals(Integer.valueOf(3), calculate);
    }
}
