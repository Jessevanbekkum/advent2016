package day18;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrapMazeTest {
    @Test
    public void doExample() {
        TrapMaze maze = new TrapMaze(".^^.^.^^^^", 10);
        maze.print();
        assertEquals(38, maze.countSafe());
    }

    @Test
    public void doInput() {
        TrapMaze maze = new TrapMaze("^^.^..^.....^..^..^^...^^.^....^^^.^.^^....^.^^^...^^^^.^^^^.^..^^^^.^^.^.^.^.^.^^...^^..^^^..^.^^^^", 40);
        maze.print();
        System.out.println(maze.countSafe());
    }

    @Test
    public void doInput2() {
        TrapMaze maze = new TrapMaze("^^.^..^.....^..^..^^...^^.^....^^^.^.^^....^.^^^...^^^^.^^^^.^..^^^^.^^.^.^.^.^.^^...^^..^^^..^.^^^^", 400000);
//        maze.print();
        System.out.println(maze.countSafe());
    }
}
