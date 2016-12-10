package day8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.Test;

import day7.Regex;

public class DisplayTest {
    Display display = new Display(7, 3);


    @Test
    public void print() {
        display.print();
    }

    @Test
    public void rect() {
        display.rect(3, 2);
        display.print();
    }

    @Test
    public void rectRot() {
        display.rect(3, 2);
        display.rotateColumn(1,1);
        display.print();
    }

    @Test
    public void rectRotRot() {
        display.rect(3, 2);
        display.rotateColumn(1,1);
        display.rotateRow(0,4);
        display.print();
    }

    @Test
    public void rectRotRotRot() {
        display.rect(3, 2);
        display.rotateColumn(1,1);
        display.rotateRow(0,4);
        display.rotateColumn(1,1);
        display.print();
    }

    @Test
    public void doExample() {
        display.command("rect 3x2");
        display.print();
        System.out.println("-");
        display.command("rotate column x=1 by 1");
        display.print();
        System.out.println("-");

        display.command("rotate row y=0 by 4");
        display.print();
        System.out.println("-");

        display.command("rotate column x=1 by 1");

        display.print();
    }

    @Test
    public void shouldDoInput2() throws URISyntaxException, IOException {
        Display display = new Display(50, 6);
        Path path = Paths.get(this.getClass().getResource("/day8/input.txt").toURI());

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(display::command);

        }
display.print();
        System.out.println(display.countLit());
    }
}
