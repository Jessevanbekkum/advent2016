package day6;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.Test;

public class HistogramTest {

    @Test
    public void shouldDoExample() throws URISyntaxException, IOException {
        Histogram histogram = new Histogram(6);
        Path path = Paths.get(this.getClass().getResource("/day6/example.txt").toURI());
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(histogram::addLine);
            System.out.println(histogram.getPasswordMostCommon());
            System.out.println(histogram.getPasswordLeastCommon());

        }
    }

    @Test
    public void shouldDoInput() throws URISyntaxException, IOException {
        Histogram histogram = new Histogram(8);
        Path path = Paths.get(this.getClass().getResource("/day6/input.txt").toURI());
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(histogram::addLine);
            System.out.println(histogram.getPasswordMostCommon());
            System.out.println(histogram.getPasswordLeastCommon());
        }
    }
}
