package day12;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;

import common.Computer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ComputerTest {

    @Test
    public void shouldDoExample() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("example.txt").toURI()));
        Computer12 computer = new Computer12(strings);
        assertThat(computer.calculate(), is(42));
    }

    @Test
    public void shouldDoInput() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("input1.txt").toURI()));
        Computer12 computer = new Computer12(strings);
        System.out.println(computer.calculate());
    }
}
