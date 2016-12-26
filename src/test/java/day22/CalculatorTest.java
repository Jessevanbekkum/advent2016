package day22;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void shouldDoInput() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("input.txt").toURI()));

        Calculator calculator = new Calculator(strings);
        System.out.println(calculator.pairs());
    }

    @Test
    public void print() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("input.txt").toURI()));

        Calculator calculator = new Calculator(strings);
        calculator.print();
    }
}
