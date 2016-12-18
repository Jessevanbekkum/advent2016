package day13;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MazeTest {

    @Test
    public void shouldDoExample() {
        Maze maze = new Maze(10, 10, 10);
        assertEquals(maze.calculateDistanceTo(7, 4), 11);
        maze.print();

    }

    @Test
    public void shouldDoInput() {
        Maze maze = new Maze(1362, 100, 100);
        System.out.println(maze.calculateDistanceTo(31, 39));
        maze.print();

    }


    @Test
    public void shouldDoInput2() {
        Maze maze = new Maze(1362, 51, 51);
        System.out.println(maze.calculatePlacesInSteps(50));
        maze.print();

    }
}
