package day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Test;
import com.google.common.collect.Lists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class Puzzle3 {

    @Test
    public void shouldDoTriangle() {
        Triangler triangler = new Triangler();
        boolean triangle = triangler.isTriangle(Lists.newArrayList(5, 10, 25));

        assertFalse(triangle);
    }

    @Test
    public void shouldDoList() throws IOException, URISyntaxException {
        Triangler triangler = new Triangler();
        Path path = Paths.get(this.getClass().getResource("/day3/input.txt").toURI());
        try (Stream<String> stream = Files.lines(path)) {
            long count = stream.map(triangler::splitLine).filter(triangler::isTriangle).count();
            System.out.println(count);
        }

    }

    @Test
    public void do3Lines() {
        Triangler triangler = new Triangler();
        ArrayList<String> lineSet1 = Lists.newArrayList("101 301 501", "102 302 502", "103 303 503");
        ArrayList<String> lineSet2 = Lists.newArrayList("201 401 5", "202 402 10", "203 403 25");
        assertEquals(triangler.trianglesInLines(lineSet1), 3);
        assertEquals(triangler.trianglesInLines(lineSet2), 2);
    }



    @Test
    public void shouldDo3List() throws IOException, URISyntaxException {
        Triangler triangler = new Triangler();
        Path path = Paths.get(this.getClass().getResource("/day3/input.txt").toURI());
        List<Integer> sums = new ArrayList<>();
        List<String> chop = new ArrayList<>();
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(s -> {
                chop.add(s);
                if (chop.size() == 3) {
                    sums.add(triangler.trianglesInLines(chop));
                    chop.clear();
                }
            });
            System.out.println(sums.stream().mapToInt(i -> i).sum());
        }

    }
}
