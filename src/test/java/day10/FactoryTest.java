package day10;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.Test;

public class FactoryTest {
    @Test
    public void example() throws IOException, URISyntaxException {
        Factory factory = new Factory();
        Path path = Paths.get(this.getClass().getResource("/day10/example.txt").toURI());

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(factory::parseLine);
            factory.distribute();
            System.out.println(factory.findDecider(2,5));
        }
    }
@Test
    public void shouldDoInput() throws IOException, URISyntaxException {
        Factory factory = new Factory();
        Path path = Paths.get(this.getClass().getResource("/day10/input.txt").toURI());

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(factory::parseLine);
            factory.distribute();
            System.out.println(factory.findDecider(17,61));

            Output o1 = factory.outputs.get(0);
            Output o2 = factory.outputs.get(1);
            Output o3 = factory.outputs.get(2);
            int i = o1.getProduct() * o2.getProduct() * o3.getProduct();
            System.out.println(i);
        }
    }

}
