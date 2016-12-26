package day15;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CapsuleTest {
    @Test
    public void shouldDoExample() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("example.txt").toURI()));

        Capsule capsule = new Capsule(strings);
        assertEquals(capsule.calculateTime(), 5);
    }

    @Test
    public void shouldDoInput() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("day22/input.txt").toURI()));

        Capsule capsule = new Capsule(strings);
        assertEquals(capsule.calculateTime(), 5);
    }

    @Test
    public void shouldDoInput2() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("input2.txt").toURI()));

        Capsule capsule = new Capsule(strings);
        System.out.println(capsule.calculateTime());
    }
}
