package day20;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FirewallTest {

    Firewall firewall = new Firewall();

    @Test
    public void shouldDoExample() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(this.getClass().getResource("example.txt").toURI()));

        assertThat(firewall.input(lines), is(3));
    }

    @Test
    public void shouldDoInput() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(this.getClass().getResource("day22/input.txt").toURI()));
        System.out.println(firewall.input(lines));
    }

    @Test
    public void shouldDoExample2() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(this.getClass().getResource("example.txt").toURI()));

        assertThat(firewall.nrOfIps(lines), is(2L));
    }
    @Test
    public void shouldDoInput2() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(this.getClass().getResource("day22/input.txt").toURI()));
        System.out.println(firewall.nrOfIps(lines));
    }
}
