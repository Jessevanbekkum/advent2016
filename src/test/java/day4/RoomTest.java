package day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoomTest {
    @Test
    public void example1() {
        Room room = new Room("aaaaa-bbb-z-y-x-123[abxyz]");
        assertEquals(room.checksum, "abxyz");
        assertEquals(room.cryptedName, "aaaaa-bbb-z-y-x");
        assertEquals(room.sector, 123);

        assertTrue(room.checksumMatches());
    }

    @Test
    public void example2() {
        Room room = new Room("a-b-c-d-e-f-g-h-987[abcde]");
        assertTrue(room.checksumMatches());
    }

    @Test
    public void example3() {
        Room room = new Room("not-a-real-room-404[oarel]");
        assertTrue(room.checksumMatches());
    }

    @Test
    public void example4() {
        Room room = new Room("totally-real-room-200[decoy]");
        assertFalse(room.checksumMatches());
    }

    @Test
    public void shouldDoList() throws IOException, URISyntaxException {
        Path path = Paths.get(this.getClass().getResource("/day4/input.txt").toURI());
        try (Stream<String> stream = Files.lines(path)) {
            int sum = stream.map(Room::new).filter(Room::checksumMatches).mapToInt(Room::getSector).sum();

            System.out.println(sum);
        }
    }

    @Test
    public void shouldDoExample5() {
Room room = new Room("qzmt-zixmtkozy-ivhz-343[abce]");
assertEquals(room.decrypt(), "very encrypted name");
    }
    @Test
    public void shouldDoList2() throws IOException, URISyntaxException {
        Path path = Paths.get(this.getClass().getResource("/day4/input.txt").toURI());
        try (Stream<String> stream = Files.lines(path)) {
            Optional<Room> first = stream.map(Room::new).filter(Room::checksumMatches).filter(r -> r.decrypt().equals("northpole object storage")).findFirst();

            first.ifPresent(x -> System.out.println(x.getSector()));
        }
    }

    @Test
    public void checkShift() {
        Character z = Room.shift(884, 'z');
        System.out.println(z);
    }
}
