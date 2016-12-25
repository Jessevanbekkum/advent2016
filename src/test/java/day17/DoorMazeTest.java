package day17;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DoorMazeTest {
    @Test
    public void shouldDoExample1() {
        DoorMaze doorMaze = new DoorMaze("ihgpwlah");
        assertEquals("DDRRRD", doorMaze.shortestPath());
    }

    @Test
    public void shouldDoExample2() {
        DoorMaze doorMaze = new DoorMaze("kglvqrro");
        assertEquals("DDUDRLRRUDRD", doorMaze.shortestPath());
    }

    @Test
    public void shouldDoExample3() {
        DoorMaze doorMaze = new DoorMaze("ulqzkmiv");
        assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", doorMaze.shortestPath());
    }

    @Test
    public void shouldDoInput() {
        DoorMaze doorMaze = new DoorMaze("mmsxrhfx");
        System.out.println(doorMaze.shortestPath());
    }

    @Test
    public void shouldDoExample4() {
        DoorMaze doorMaze = new DoorMaze("ihgpwlah");
        assertEquals(370, doorMaze.longestPath());
    }

    @Test
    public void shouldDoExample5() {
        DoorMaze doorMaze = new DoorMaze("kglvqrro");
        assertEquals(492, doorMaze.longestPath());
    }

    @Test
    public void shouldDoExample6() {
        DoorMaze doorMaze = new DoorMaze("ulqzkmiv");
        assertEquals(830, doorMaze.longestPath());
    }

    @Test
    public void shouldDoInput2() {
        DoorMaze doorMaze = new DoorMaze("mmsxrhfx");
        System.out.println(doorMaze.longestPath());
    }

}
