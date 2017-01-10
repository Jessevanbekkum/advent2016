package day24;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;
import com.google.common.collect.Lists;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RobotMazeTest {
    @Test
    public void printExample() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("example.txt").toURI()));
        RobotMaze robotMaze = new RobotMaze(strings);
        int i = robotMaze.doTSP();
        assertThat(i, is(14));
    }


    @Test
    public void doInput() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("input.txt").toURI()));
        RobotMaze robotMaze = new RobotMaze(strings);
        int i = robotMaze.doTSP();
        System.out.println(i);
    }
    @Test
    public void doInputWithReturn() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("input.txt").toURI()));
        RobotMaze robotMaze = new RobotMaze(strings);
        int i = robotMaze.doTSPWithReturn();
        System.out.println(i);
    }

    @Test
    public void shouldCalcDistance() throws URISyntaxException, IOException {
        List<String> strings = Files.readAllLines(Paths.get(this.getClass().getResource("example.txt").toURI()));
        RobotMaze robotMaze = new RobotMaze(strings);
        int i = robotMaze.calcDistance(Lists.newArrayList(0, 4, 1, 2, 3));
        assertThat(i, is(14));
    }

    @Test
    public void shouldCalculatePermutations() throws URISyntaxException, IOException {

        List<List<Integer>> generator = RobotMaze.generator(Lists.newArrayList(1, 2, 3, 4, 5));
        assertThat(generator.size(), is(120));
    }

    @Test
    public void shouldCalcPerms2() throws URISyntaxException, IOException {
        List<Integer> l = Lists.newArrayList(2, 1, 0, 3, 4);
        assertThat(RobotMaze.generator(Lists.newArrayList(0, 1, 2, 3 ,4)).contains(l), is(true));
    }
}
