package day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DecompressTest {

    Decompress decompress = new Decompress();

    @Test
    public void shouldDoExample1() {
        assertThat(decompress.length("ADVENT"), is(6));
    }

    @Test
    public void shouldDoExample2() {
        assertThat(decompress.length("A(1x5)BC"), is(7));

    }

    @Test
    public void shouldDoExample3() {
        assertThat(decompress.length("(3x3)XYZ"), is(9));

    }

    @Test
    public void shouldDoExample4() {
        assertThat(decompress.length("A(2x2)BCD(2x2)EFG"), is(11));

    }

    @Test
    public void shouldDoExample5() {
        assertThat(decompress.length("(6x1)(1x3)A"), is(6));

    }

    @Test
    public void shouldDoExample6() {
        assertThat(decompress.length("X(8x2)(3x3)ABCY"), is(18));

    }

    @Test
    public void shouldDoInput() throws IOException, URISyntaxException {
        Decompress decompress = new Decompress();
        Path path = Paths.get(this.getClass().getResource("/day9/input.txt").toURI());

        try (Stream<String> stream = Files.lines(path)) {
            int sum = stream.mapToInt(decompress::length).sum();
            System.out.println(sum);
        }
    }


    @Test
    public void shouldDoExamplev2_1() {
        assertThat(decompress.lengthv2("(3x3)XYZ"), is(9));

    }

    @Test
    public void shouldDoExamplev2_2() {
        assertThat(decompress.lengthv2("X(8x2)(3x3)ABCY"), is(20));

    }

    @Test
    public void shouldDoExamplev2_3() {
        assertThat(decompress.lengthv2("(27x12)(20x12)(13x14)(7x10)(1x12)A"), is(241920));
    }


    @Test
    public void shouldDoExamplev2_4() {
        assertThat(decompress.lengthv2("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN"), is(445));

    }

    @Test
    public void shouldDoInputV2() throws IOException, URISyntaxException {
        Decompress decompress = new Decompress();
        Path path = Paths.get(this.getClass().getResource("/day9/input.txt").toURI());

        try (Stream<String> stream = Files.lines(path)) {
            long sum = stream.mapToLong(decompress::lengthv2).sum();
            System.out.println(sum);
        }
    }
}
